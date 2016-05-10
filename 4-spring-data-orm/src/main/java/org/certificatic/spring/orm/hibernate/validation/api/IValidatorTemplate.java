package org.certificatic.spring.orm.hibernate.validation.api;

import java.util.List;

import org.certificatic.spring.orm.hibernate.exceptions.ValidationError;
import org.certificatic.spring.orm.hibernate.exceptions.ValidatorException;
import org.springframework.validation.Errors;

public interface IValidatorTemplate {

	void validate(final Object target, final Class<?>... validatorGroups) throws ValidatorException;

	void validate(final Object target, final Errors errors, final List<ValidationError> listValidationError,
			final Class<?>... validatorGroups) throws ValidatorException;
}
