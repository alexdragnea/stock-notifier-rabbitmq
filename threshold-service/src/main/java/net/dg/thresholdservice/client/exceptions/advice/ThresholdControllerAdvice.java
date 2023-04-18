package net.dg.thresholdservice.client.exceptions.advice;

import feign.RetryableException;
import net.dg.thresholdservice.client.exceptions.ApiErrorResponse;
import net.dg.thresholdservice.client.exceptions.StockFetcherApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ThresholdControllerAdvice {

  @ExceptionHandler(StockFetcherApiException.class)
  public ResponseEntity<ApiErrorResponse> handleTwelveApiException(StockFetcherApiException ex) {
    HttpStatus status = HttpStatus.resolve(ex.getStatusCode());
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
