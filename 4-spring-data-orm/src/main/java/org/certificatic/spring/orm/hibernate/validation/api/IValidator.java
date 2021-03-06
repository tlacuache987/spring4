package org.certificatic.spring.orm.hibernate.validation.api;

import java.util.List;

import org.certificatic.spring.orm.hibernate.exceptions.ValidationError;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface IValidator extends Validator {

	void validate(final Object target, final Errors errors, final List<ValidationError> listValidationError);

	void validate(final Object target, final Errors errors, final List<ValidationError> listValidationError,
			final Class<?>... validatorGroups);

}
