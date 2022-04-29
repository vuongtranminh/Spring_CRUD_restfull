package com.tranminhvuong.restful.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescriptionValidator implements ConstraintValidator<DescriptionAnnotation, String> {

    private static final String DESCRIPTION_PREFIX = "Good";

    /**
     * Kiểm tra tính hợp lệ của trường được đánh dấu bởi @LodaId
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) return false;

        return s.startsWith(DESCRIPTION_PREFIX);
    }

}
