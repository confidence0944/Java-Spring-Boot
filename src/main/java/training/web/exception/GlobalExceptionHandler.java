package training.web.exception;

import training.web.Enums.ReturnCode;
import training.web.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.validation.ConstraintViolationException;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===== Validation (JSON Body) =====
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return ApiResponse.error(ReturnCode.BAD_REQUEST, msg);
    }

    // ===== Validation (Query Param) =====
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleConstraint(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .findFirst()
                .orElse(ex.getMessage());
        return ApiResponse.error(ReturnCode.VALIDATION_FAILED, msg);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<?> handleEntityNotFound(EntityNotFoundException ex) {
        return ApiResponse.error(ReturnCode.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse<?> handleDuplicateEntity(DuplicateEntityException ex) {
        return ApiResponse.error(ReturnCode.CONFLICT, ex.getMessage());
    }

    // ===== fallback =====
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleUnknown(Exception ex) {
        return ApiResponse.error(ReturnCode.UNKNOWN_ERROR, ex.getMessage());
    }
}