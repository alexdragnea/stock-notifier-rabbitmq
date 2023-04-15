package net.dg.stockfetcherservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TwelveApiError {
  BAD_REQUEST(400, "There is an error with one or multiple parameters."),
  NOT_FOUND(404, "The specified data can not be found."),
  PARAMETER_TOO_LONG(414, "The parameter which accepts multiple values is out of range."),
  TOO_MANY_REQUESTS(429, "You've reached your API request limits."),
  INTERNAL_SERVER_ERROR(500, "There is an error on the server-side. Try again later.");
  private final int statusCode;

  private final String message;
}
