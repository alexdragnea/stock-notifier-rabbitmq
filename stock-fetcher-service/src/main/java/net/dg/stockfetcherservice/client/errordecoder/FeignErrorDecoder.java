package net.dg.stockfetcherservice.client.errordecoder;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.util.Date;
import org.springframework.http.HttpStatus;

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
    }
    return defaultErrorDecoder.decode(methodKey, response);
  }
}
