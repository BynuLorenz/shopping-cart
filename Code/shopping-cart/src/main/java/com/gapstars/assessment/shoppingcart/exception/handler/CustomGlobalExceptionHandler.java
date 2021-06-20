
package com.gapstars.assessment.shoppingcart.exception.handler;

import com.gapstars.assessment.shoppingcart.common.enums.ResponseCode;
import com.gapstars.assessment.shoppingcart.controller.payload.response.Response;
import com.gapstars.assessment.shoppingcart.exception.BaseException;
import com.gapstars.assessment.shoppingcart.exception.CartNotFoundException;
import com.gapstars.assessment.shoppingcart.exception.CustomerNotFoundException;
import com.gapstars.assessment.shoppingcart.exception.ProductNotFoundException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** The CustomGlobalExceptionHandler is the exception handle used for validation exception handling */
@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler  {

  /**
   * Exception handler method for Exception type - BaseException
   * @param ex Base Exception
   * @return Common Response
   */
  @ExceptionHandler( BaseException.class )
  public ResponseEntity<Object> handleBaseException( BaseException ex ) {

    String msg = ex.getErrorMsg();
    Response response = new Response();
    response.setResponseCode( ResponseCode.FAIL.getCode() );
    response.setResponseMsg( msg );
    log.error( "BaseException {}", ex.getMessage() );
    return new ResponseEntity<>( response, HttpStatus.OK );
  }

  /**
   * Exception handler method for Exception type - CustomerNotFoundException
   * @param ex Customer Not Found Exception
   * @return Common Response
   */
  @ExceptionHandler( CustomerNotFoundException.class )
  public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {

    String msg = ex.getErrorMsg();
    Response response = new Response();
    response.setResponseCode( ResponseCode.FAIL.getCode() );
    response.setResponseMsg(msg);
    log.error("CustomerNotFoundException {}", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Exception handler method for Exception type - CustomerNotFoundException
   * @param ex Product Not Found Exception
   * @return Common Response
   */
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {

    String msg = ex.getErrorMsg();
    Response response = new Response();
    response.setResponseCode( ResponseCode.FAIL.getCode() );
    response.setResponseMsg(msg);
    log.error("ProductNotFoundException {}", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Exception handler method for Exception type - CartNotFoundException
   * @param ex Cart Not Found Exception
   * @return Common Response
   */
  @ExceptionHandler(CartNotFoundException.class)
  public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException ex) {

    String msg = ex.getErrorMsg();
    Response response = new Response();
    response.setResponseCode( ResponseCode.FAIL.getCode() );
    response.setResponseMsg(msg);
    log.error("CartNotFoundException {}", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Exception handler method for Exception type - MethodArgumentNotValidException
   * @param ex MethodArgumentNotValidException
   * @return Common Response
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> test(MethodArgumentNotValidException ex) {

    String joinedErrors = ex.getBindingResult().getFieldErrors().stream()
        .map( fieldError -> fieldError.getDefaultMessage() )
        .collect(Collectors.joining(", "));

    Response response = new Response();
    response.setResponseCode( ResponseCode.FAIL.getCode() );
    response.setResponseMsg(joinedErrors);
    log.error("MethodArgumentNotValidException {}", joinedErrors);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception handler method for Exception type - RuntimeException
   * @param ex RuntimeException
   * @return Common Response
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {

    log.error("RuntimeException", ex);
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Exception handler method for Exception type - Generic Exception
   * @param ex Generic Exception
   * @return Common Response
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleResourceNotFountException(Exception ex) {

    log.error("Exception", ex);
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
