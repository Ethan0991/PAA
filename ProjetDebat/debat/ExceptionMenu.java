package ProjetDebat.debat;

/**
 * Classe permettant de créer des exceptions personnalisées & liées à l'utlisation de la classe menu
 *
 * @author Ethan & Lathan
 */
public class ExceptionMenu extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg ;
	
	public ExceptionMenu(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
