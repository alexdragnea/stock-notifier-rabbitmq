package net.dg.stockfetcherservice.exceptions;

import lombok.Getter;
import net.dg.stockfetcherservice.enums.TwelveApiError;

@Getter
public class TwelveApiException extends RuntimeException {

  private final TwelveApiError twelveApiError;

  public TwelveApiException(TwelveApiError twelveApiError) {
    super(twelveApiError.getMessage());
    this.twelveApiError = twelveApiError;
  }
}
