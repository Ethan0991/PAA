package ProjetDebat.debat;

/**
 * Classe permettant de créer des exceptions personnalisées & liées à l'utlisation de la classe Débat
 *
 * @author Ethan & Lathan
 */
public class ExceptionDebat extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg ;
	
	public ExceptionDebat(String msg) {
		this.msg = msg;
	}
	

	public String getMessage() {
		return msg;
	}
}
