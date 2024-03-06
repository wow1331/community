package com.boot.community.validator;

import com.boot.community.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    public boolean supports(Class clazz) {
        return Board.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "content", "empty","내용을 입력하세요");
    }
}