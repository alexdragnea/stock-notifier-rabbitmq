package net.dg.thresholdservice.client.errordecoder;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import net.dg.thresholdservice.client.exceptions.StockFetcherApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {

    if (response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()
        || response.status() == HttpStatus.REQUEST_TIMEOUT.value()) {

      return new RetryableException(
          response.status(),
          methodKey,
          null,
          new Date(System.currentTimeMillis()),
          response.request());
    } else {
      String message = readResponseBodyAsString(response);
      return new StockFetcherApiException(message, response.status());
    }
  }

  private String readResponseBodyAsString(Response response) {
    String responseBody = "";
    try {
      if (response.body() != null) {
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(response.body().asInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          sb.append(line);
        }
        responseBody = sb.toString();
      }
    } catch (IOException e) {
      // Handle IOException
    }
    return responseBody;
  }
}
