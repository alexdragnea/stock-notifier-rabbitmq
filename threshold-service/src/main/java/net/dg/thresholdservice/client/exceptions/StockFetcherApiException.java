package net.dg.thresholdservice.client.exceptions;

import lombok.Getter;

@Getter
public class StockFetcherApiException extends RuntimeException {

  private final int statusCode;

  public StockFetcherApiException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }
}
