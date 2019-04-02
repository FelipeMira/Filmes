package br.com.felipemira.error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * @author felipe.mira.ext 01/04/2019
 */
public final class ValidationErrorDTO {
	private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();

	public ValidationErrorDTO() {

	}

	public void addFieldError(String path, String message) {
		FieldErrorDTO error = new FieldErrorDTO(path, message);
		fieldErrors.add(error);
	}

	public List<FieldErrorDTO> getFieldErrors() {
		return Collections.unmodifiableList(fieldErrors);
	}
}
