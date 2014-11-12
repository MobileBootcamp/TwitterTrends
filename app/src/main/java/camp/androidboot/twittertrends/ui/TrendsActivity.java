package camp.androidboot.twittertrends.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import camp.androidboot.twittertrends.R;
import camp.androidboot.twittertrends.TwitterTrendsApplication;
import camp.androidboot.twittertrends.model.Trend;
import camp.androidboot.twittertrends.network.TwitterLite;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 */
public class TrendsActivity extends Activity implements TrendListFragment.Callbacks {

  private static final String FRAGMENT_TAG = "fragment_tag";

  private TwitterLite twitterLite;

  private TrendListFragment trendListFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_trends);

    twitterLite = ((TwitterTrendsApplication) getApplication()).getTwitterLite();
    addInitialFragment();
    getTrends();
  }

  private void addInitialFragment() {
    trendListFragment = TrendListFragment.newInstance();

    getFragmentManager()
        .beginTransaction()
        .add(R.id.content_frame, trendListFragment, FRAGMENT_TAG)
        .commit();
  }

  private void getTrends() {
    twitterLite.trends(new Callback<List<Trend>>() {
      @Override
      public void success(List<Trend> trends, Response response) {
        trendListFragment.addTrends(trends);
      }

      @Override
      public void failure(RetrofitError error) {
        Toast.makeText(TrendsActivity.this, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onTrendClick(Trend trend) {
    Toast.makeText(this, "trend: " + trend.getName(), Toast.LENGTH_SHORT).show();
  }
}
