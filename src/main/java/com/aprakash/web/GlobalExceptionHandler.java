package com.aprakash.web;

import com.aprakash.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handlemethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> invalidFields = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList());
        return new ErrorDto(exception.getMessage(), invalidFields);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDto handleConstraintViolation(ConstraintViolationException exception) {
        List<String> invalidFields = exception.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath().toString())
                .collect(Collectors.toList());
        return new ErrorDto(exception.getMessage(), invalidFields);
    }

}