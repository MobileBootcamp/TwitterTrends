package camp.androidboot.twittertrends.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 */
public class Trend {

  private String name;

  public String getName() {
    return name;
  }

  public String getQueryString() {
    try {
      return URLEncoder.encode(name, "utf-8");
    } catch (UnsupportedEncodingException e) {
      throw new AssertionError("UTF-8 is not supported");
    }
  }
}
