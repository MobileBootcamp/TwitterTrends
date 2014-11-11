package camp.androidboot.twittertrends;

import android.app.Application;
import camp.androidboot.twittertrends.network.TwitterLite;
import retrofit.RestAdapter;

/**
 *
 */
public class TwitterTrendsApplication extends Application {

  private static final String API_URL =
      "https://raw.githubusercontent.com/MobileBootcamp/TwitterTrends/master/app/src/main/assets/api/";

  private TwitterLite twitterLite;

  @Override
  public void onCreate() {
    super.onCreate();

    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(API_URL)
        .build();

    twitterLite = restAdapter.create(TwitterLite.class);
  }

  /**
   *
   * @return
   */
  public TwitterLite getTwitterLite() {
    return twitterLite;
  }
}
