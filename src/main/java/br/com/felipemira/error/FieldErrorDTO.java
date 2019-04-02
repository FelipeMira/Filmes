package br.com.felipemira.error;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public final class FieldErrorDTO {
	private final String field;

	private final String message;

	FieldErrorDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
