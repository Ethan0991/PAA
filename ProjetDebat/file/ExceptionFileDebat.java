package ProjetDebat.file;

/**
 * Classe permettant de créer des exceptions personnalisées & liées à l'utlisation de la classe FichierDebat
 *
 * @author Ethan & Lathan
 */
public class ExceptionFileDebat extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg ;
	
	public ExceptionFileDebat(String msg) {
		this.msg = msg;
	}
	

	public String getMessage() {
		return msg;
	}
}
