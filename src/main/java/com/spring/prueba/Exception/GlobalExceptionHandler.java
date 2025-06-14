package com.spring.prueba.Exception;

import com.spring.prueba.DTO.ApiErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            Exception.class,
            ObjectNotFoundException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class,
            NoHandlerFoundException.class
    })

    public ResponseEntity<ApiErrorDTO> handleAllException(Exception exception, HttpServletRequest request, HttpServletResponse response){

        ZoneId zoneId = ZoneId.of("America/Bogota");
        LocalDateTime timestamp = LocalDateTime.now(zoneId);

        if (exception instanceof ObjectNotFoundException objectNotFoundException) {
            return this.handleObjectNotFoundException(objectNotFoundException, request, response, timestamp);
        }

        if (exception instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            return this.handledMethodArgumentTypeMismatchException(methodArgumentTypeMismatchException, request, response, timestamp);
        }

        if (exception instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            return this.handledMethodArgumentNotValidException(methodArgumentNotValidException, request, response, timestamp);
        }

        if (exception instanceof HttpMessageNotReadableException httpMessageNotReadableException) {
            return this.handleHttpMessageNotReadableException(httpMessageNotReadableException, request, response, timestamp);
        }

        if (exception instanceof NoHandlerFoundException noHandlerFoundException) {
            return this.handleNoHandlerFoundException(noHandlerFoundException, request, response, timestamp);
        }

        return this.handleException(exception, request, response, timestamp);
    }



    private ResponseEntity<ApiErrorDTO> handleObjectNotFoundException(ObjectNotFoundException objectNotFoundException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.NOT_FOUND.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage(objectNotFoundException.getMessage());
        apiErrorDto.setBackendMessage(objectNotFoundException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Opps! Something went wrong on our server. Please try again later ");
        apiErrorDto.setBackendMessage(exception.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handledMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();
        Object valueRejected = methodArgumentTypeMismatchException.getValue();
        String propertyName = methodArgumentTypeMismatchException.getName();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Invalid Request: The provided value '" + valueRejected + "' does not have the expected data type for the '" + propertyName + "'.");
        apiErrorDto.setBackendMessage(methodArgumentTypeMismatchException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handledMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();

        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        List<String> details = errors.stream().map(error -> {

            if (error instanceof FieldError fieldError) {
                return fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }

            return error.getDefaultMessage();

        }).toList();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("The request contains invalid or incomplete parameters. Please verify and provide the required information before trying again.");
        apiErrorDto.setBackendMessage(methodArgumentNotValidException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(details);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Oops! Error reading the HTTP message body. Make sure the request is correctly formatted and contains valid data." + httpMessageNotReadableException.getMessage());
        apiErrorDto.setBackendMessage(httpMessageNotReadableException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);

    }


    public ResponseEntity<ApiErrorDTO> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp) {


        int httpStatus = HttpStatus.NOT_FOUND.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("The requested endpoint does not exist.");
        apiErrorDto.setBackendMessage(ex.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

}
