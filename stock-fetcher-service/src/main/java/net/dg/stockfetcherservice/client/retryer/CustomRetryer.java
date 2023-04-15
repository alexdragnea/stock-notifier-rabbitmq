package net.dg.stockfetcherservice.client.retryer;

import feign.RetryableException;
import feign.Retryer;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomRetryer implements Retryer {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomRetryer.class);

  private final int maxAttempts;

  private final long backoff;

  int attempt;

  /** Waits for 10 second before retrying. */
  public CustomRetryer() {
    this(1000, 3);
  }

  public CustomRetryer(long backoff, int maxAttempts) {
    this.backoff = backoff;
    this.maxAttempts = maxAttempts;
    this.attempt = 0;
  }

  public void continueOrPropagate(RetryableException e) {

    if (attempt++ >= maxAttempts) {
      throw e;
    }

    try {
      TimeUnit.MILLISECONDS.sleep(backoff);
    } catch (InterruptedException ex) {

    }

    LOGGER.info("Retrying: " + e.request().url() + " attempt " + attempt);
  }

  @Override
  public Retryer clone() {
    return new CustomRetryer(backoff, maxAttempts);
  }
}
