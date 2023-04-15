package net.dg.stockfetcherservice.service;

import net.dg.stockfetcherservice.enums.TwelveApiError;
import net.dg.stockfetcherservice.exceptions.TwelveApiException;
import net.dg.stockfetcherservice.model.TwelveResponseBody;
import org.springframework.stereotype.Service;

@Service
public class TwelveValidationService {

  public void validateApiResponse(TwelveResponseBody twelveResponseBody) throws TwelveApiException {
    String code = twelveResponseBody.getCode();
    if (code != null && !code.isEmpty()) {
      switch (code) {
        case "400":
          throw new TwelveApiException(TwelveApiError.BAD_REQUEST);
        case "404":
          throw new TwelveApiException(TwelveApiError.NOT_FOUND);
        case "414":
          throw new TwelveApiException(TwelveApiError.PARAMETER_TOO_LONG);
        case "429":
          throw new TwelveApiException(TwelveApiError.TOO_MANY_REQUESTS);
        default:
          throw new TwelveApiException(TwelveApiError.INTERNAL_SERVER_ERROR);
      }
    }
  }
}
