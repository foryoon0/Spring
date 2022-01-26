package spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.vo.ChangePwdCommand;

public class ChangePwdCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePwdCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ChangePwdCommand cpc = (ChangePwdCommand)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "required");
		
		if(cpc.getCurrentPassword()!=null && !cpc.getCurrentPassword().trim().isEmpty() ) {
			if(cpc.getCurrentPassword().equals(cpc.getNewPassword())) {
				errors.reject("passwordMatchingFail.1");
				errors.reject("passwordMatchingFail.2");
			}
		}
		
	}

}
