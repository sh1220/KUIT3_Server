package kuit.server.common.exception_handler;


import jakarta.annotation.Priority;
import kuit.server.common.exception.RestaurantException;
import kuit.server.common.exception.UserException;
import kuit.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@Priority(0)
@RestControllerAdvice
public class RestaurantExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RestaurantException.class)
    public BaseErrorResponse handle_RestaurantException(RestaurantException e){
        return new BaseErrorResponse(e.getExceptionStatus(), e.getMessage());
    }
}
