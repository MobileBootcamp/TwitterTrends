package camp.androidboot.twittertrends.network;

import camp.androidboot.twittertrends.model.Status;
import camp.androidboot.twittertrends.model.Trend;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 *
 */
public interface TwitterLite {

  @GET("/trends") void trends(Callback<List<Trend>> trends);

  @GET("/search") void search(@Query("query") String query, Callback<List<Status>> statuses);
}
