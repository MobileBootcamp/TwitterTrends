package camp.androidboot.twittertrends.network;

import camp.androidboot.twittertrends.model.Status;
import camp.androidboot.twittertrends.model.Trend;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;

/**
 *
 */
public interface TwitterLite {

  @GET("/trends") void trends(Callback<List<Trend>> trends);

  @GET("/search") void search(String query, Callback<List<Status>> statuses);
}
