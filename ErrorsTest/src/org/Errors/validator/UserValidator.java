package org.Errors.validator;

import org.Errors.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    public boolean supports(Class<?> clazz){
        return User.class.equals(clazz);
    }
    //用于验证数据
    @Override
    public void validate(Object object, Errors errors) {
        //验证username,sex,和age是否为null
        ValidationUtils.rejectIfEmpty(errors,"username",null,"用户名不能为空");
        ValidationUtils.rejectIfEmpty(errors,"sex",null,"性别不能为空");
        ValidationUtils.rejectIfEmpty(errors,"age",null,"年龄不能为空");
    }
}
