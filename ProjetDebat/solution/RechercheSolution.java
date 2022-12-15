package ProjetDebat.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ProjetDebat.graphe.Argument;
import ProjetDebat.graphe.Graphe;
import ProjetDebat.util.*;

/**
 * Classe permettant la recherche d'une solution
 * Contient en paramètre tous les ensembles nécessaires à cette recherche
 *
 * @author Ethan & Lathan
 */
public class RechercheSolution {

	private Graphe grapheArg;
	private ArrayList<ArrayList<Argument>> listeCombinaisonsArguments ;
	private List<HashSet<Argument>> listeSolutionsAdmissible ;
	private List<HashSet<Argument>> listeSolutionsPreferee ;
	private int cptSolAdmissible =0;
	private int cptSolPreferee = 0;
	private VerifSolution vf;
	
    /**
    * Construit une classe recherche en fonction d'un graphe passé en paramètre
    *
    * @param grapheArg est un graphe à partir duquel faire la recherche de solution
    */
	public RechercheSolution(Graphe grapheArg) {
		
		this.grapheArg = grapheArg;
		listeCombinaisonsArguments = new ArrayList<ArrayList<Argument>>();
		listeSolutionsAdmissible = new ArrayList<HashSet<Argument>>();
		listeSolutionsPreferee = new ArrayList<HashSet<Argument>>();
		vf = new VerifSolution(null, grapheArg,true);
	}
	
    /**
    * Construit une liste de solution admissible
    * en vérifiant que chacun des arguments respectent les conditions d'une solution admissible
    */
	public void construireListeSolutionAdmissible() {
		HashSet<Argument> ensSolutionPotentielle ;
		HashSet<Argument> ensVide = new HashSet<>();
		ensVide.add(new Argument("Ensemble vide"));
		listeSolutionsAdmissible.add(ensVide);
		for (List<Argument> solutionPotentielle : listeCombinaisonsArguments) {
			ensSolutionPotentielle = new HashSet<Argument>(solutionPotentielle);
			vf.setSolutionPotentielle(ensSolutionPotentielle);

			if (vf.verifSolutionAdmissible()) {
				listeSolutionsAdmissible.add(ensSolutionPotentielle);
			}
		}
		
	}
    
    /**
    * Construit une liste de solution préféree
    * en vérifiant que chacun des arguments respectent les conditions d'une solution préférée
    */
	public void construireListeSolutionPrefere() {
		
		boolean ensvide = true ;
		for (HashSet<Argument> solutionPotentiellePreferee : listeSolutionsAdmissible) {

			vf.setSolutionPotentielle(solutionPotentiellePreferee);

			if (vf.verifSolutionPrefere(listeSolutionsAdmissible)) {
				if (ensvide) {
					ensvide = false;
					continue;
				}
				listeSolutionsPreferee.add(solutionPotentiellePreferee);		
			}
		}

	}
    
    /**
    *  permet de retoruner une solution admissible de la liste des solutions admissibles
    *
    * @return listeSolutionsAdmissible.get(cptSolAdmissible++) qui est un argument admissible de listeSolutionsAdmissible
    */
	public Set<Argument> getAdmissible() {

		if (cptSolAdmissible == listeSolutionsAdmissible.size()) {
			cptSolAdmissible = 0;
		}
		
		return listeSolutionsAdmissible.get(cptSolAdmissible++);
		
	}
    
    /**
    *  permet de retoruner une solution préférée de la liste des solutions admissibles
    *
    * @return listeSolutionsPreferee.get(cptSolPreferee++) qui est un argument préférée de listeSolutionsPreferee
    */
	public Set<Argument> getPreferee() {

		if (cptSolPreferee == listeSolutionsPreferee.size()) {
			cptSolPreferee = 0;
		}

		return listeSolutionsPreferee.get(cptSolPreferee++);
		
	}
	
    /**
     * Permet de construire la liste de combinaison à partir de listeSousCombinaisonsArguments
     */
	public void construireListeCombinaisons() {
		ArrayList<Argument> listArguments = new ArrayList<>();
		ArrayList<Argument> listeCombinaison = new ArrayList<>();
		ArrayList<ArrayList<Argument>> listeSousCombinaisonsArguments ;
		
		
		for (Argument argument : grapheArg.getGraphe().keySet()) {
			listArguments.add(argument);
		}
		for (int tailleCombinaison = 1; tailleCombinaison <= listArguments.size(); tailleCombinaison++) {
			listeSousCombinaisonsArguments = new ArrayList<ArrayList<Argument>>() ;
			Combinaison.combinaisonUtil(listArguments, listeCombinaison,listeSousCombinaisonsArguments, 0, listArguments.size()-1, 0, tailleCombinaison,0);
			listeCombinaisonsArguments.addAll(listeSousCombinaisonsArguments);
		}
		
	}

    /**
     * permet de retourner l'attribut privé listeCombinaisonsArguments
     *
     * @return listeCombinaisonsArguments est une liste contenant des arguments pouvant être des solutions admissibles
     */
	public ArrayList<ArrayList<Argument>> getListeCombinaisonsArguments() {
		return listeCombinaisonsArguments;
	}
	
    /**
    * Permet d'affichier à l'utilisateur l'ensemble des solutions admissibles à partir de listeSolutionsAdmissible
    *
    */
	public void afficheListeSolutionAdmissible() {
		int cpt=1;
		for (HashSet<Argument> solutionAdmissible : listeSolutionsAdmissible) {
			System.out.println(cpt+") "+ solutionAdmissible);
			cpt++;
		}
	}
    
    /**
    * Permet d'affichier à l'utilisateur l'ensemble des solutions préférées à partir de listeSolutionsAdmissible
    *
    */
	public void afficheListeSolutionPreferee() {
		int cpt=1;
		for (HashSet<Argument> solutionAdmissible : listeSolutionsPreferee) {
			System.out.println(cpt+") "+ solutionAdmissible);
			cpt++;
		}
	}
	
}
