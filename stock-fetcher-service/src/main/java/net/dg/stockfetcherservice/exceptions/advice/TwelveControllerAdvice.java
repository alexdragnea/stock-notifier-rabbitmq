package net.dg.stockfetcherservice.exceptions.advice;

import feign.FeignException;
import feign.RetryableException;
import net.dg.stockfetcherservice.exceptions.ApiErrorResponse;
import net.dg.stockfetcherservice.exceptions.TwelveApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TwelveControllerAdvice {

  @ExceptionHandler(FeignException.class)
  public ResponseEntity<ApiErrorResponse> handleFeignException(FeignException ex) {
    HttpStatus status = HttpStatus.resolve(ex.status());
    return ResponseEntity.status(status)
        .body(new ApiErrorResponse(status.value(), ex.getMessage()));
  }

  @ExceptionHandler(TwelveApiException.class)
  public ResponseEntity<ApiErrorResponse> handleTwelveApiException(TwelveApiException ex) {
    HttpStatus status = HttpStatus.resolve(ex.getTwelveApiError().getStatusCode());
    return ResponseEntity.status(status)
        .body(new ApiErrorResponse(status.value(), ex.getMessage()));
  }

  @ExceptionHandler({RetryableException.class})
  public ResponseEntity<ApiErrorResponse> handleRetryableException(RetryableException ex) {
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .body(
            new ApiErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Service unavailable at the moment, please try again later"));
  }
}
