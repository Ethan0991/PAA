package ProjetDebat.graphe;


/**
 * Modélise un argument avec son nom et son numéro unique
 *
 * @author Ethan & Lathan
 */
public class Argument implements Comparable<Argument> {
	
	private static int numArg=0 ;
	
	private String nom;
	private int num=0;
	//private List<Argument> argContradiction ;
	
	/**
	 * Construit un argument en fonction de son contenu
	 * @param arg le nom de l'argument
	 */
	
	public Argument(String nom) {
		
		//argContradiction =  new ArrayList<Argument>();
		this.nom=nom;
		num = numArg;
		numArg++;
	}

	public String toString() {
		return "Argument " + nom ;
	}

	/**
	 * Compare le numero de cet argument avec le numéro de l'argument passé en paramètre
	 * 
	 * @param A est le 2eme argument avec lequel la comparaison doit être faite
	 * @return le résultat de la différence des numéros des 2 arguments 
	 */
	@Override
	public int compareTo(Argument A) {
		
		return this.num - A.num;
	}
	
	/**
	 * permet d'obtenir le num unique attribué à l'argument crée
	 * 
	 * @return le num attribué à l'argument
	 */
	public int getNum() {
		return num;
	}

    /**
    * permet de retoruner l'aatribut privé nom
    *
    * @return nom est le nom que porte l'argument
    */
	public String getNom() {
		return nom;
	}
	
	
	
	

	
}
