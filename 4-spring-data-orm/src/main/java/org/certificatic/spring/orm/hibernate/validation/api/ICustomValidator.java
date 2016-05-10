package org.certificatic.spring.orm.hibernate.validation.api;

import java.util.List;

import org.certificatic.spring.orm.hibernate.exceptions.ValidationError;
import org.springframework.validation.Errors;

public interface ICustomValidator {
	boolean isValid(final Errors errors, final Object object, List<ValidationError> listValidationError,
			final Class<?>... validatorGroups);
}
