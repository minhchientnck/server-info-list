package home.exception;

public class NotFoundException extends BaseException {
	private static final long serialVersionUID = 4928279262203799937L;

	public NotFoundException(String message) {
		super(message);
	}
}
