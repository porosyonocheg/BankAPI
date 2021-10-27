package ru.sberbank.bankapi.utils;

import ru.sberbank.bankapi.model.dto.RequestCardDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class CheckingData {
    public static void isValid(RequestCardDTO cardDTO) {
        Validator validator = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RequestCardDTO>> violationSet = validator.validate(cardDTO);
        if (!violationSet.isEmpty()) {
            throw new IllegalArgumentException("There are null-fields in the request");
        }
    }
}
