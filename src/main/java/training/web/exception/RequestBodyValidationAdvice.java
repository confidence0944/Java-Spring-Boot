package training.web.exception;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import jakarta.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RequestBodyValidationAdvice extends RequestBodyAdviceAdapter {

    private final Validator validator;

    @Autowired
    public RequestBodyValidationAdvice(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Type targetType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(RequestBody.class);  //驗證有 @RequestBody 的參數
    }

    @Override
    public @NonNull Object afterBodyRead(@NonNull Object body, @NonNull HttpInputMessage inputMessage,
                                @NonNull MethodParameter parameter, @NonNull Type targetType,
                                @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        if (body != null) {
            Set<ConstraintViolation<Object>> violations = validator.validate(body);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }
        return body;
    }
}
