package camp.androidboot.twittertrends.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import camp.androidboot.twittertrends.R;
import camp.androidboot.twittertrends.TwitterTrendsApplication;
import camp.androidboot.twittertrends.model.Status;
import camp.androidboot.twittertrends.model.Trend;
import camp.androidboot.twittertrends.network.TwitterLite;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 *
 */
public class TrendsActivity extends Activity
    implements TrendListFragment.Callbacks, StatusListFragment.Callbacks {

  private TwitterLite twitterLite;

  private TrendListFragment trendListFragment;
  private StatusListFragment statusListFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_trends);

    twitterLite = ((TwitterTrendsApplication) getApplication()).getTwitterLite();
    addTrendFragment();
    getTrends();
  }

  /**
   *
   */
  private void addTrendFragment() {
    trendListFragment = TrendListFragment.newInstance();

    getFragmentManager()
        .beginTransaction()
        .add(R.id.content_frame, trendListFragment)
        .addToBackStack(null)
        .commit();
  }

  /**
   *
   */
  private void addStatusFragment(Trend trend) {
    statusListFragment = StatusListFragment.newInstance(trend);

    getFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, statusListFragment)
        .addToBackStack(null)
        .commit();

    getStatuses(trend);
  }

  /**
   *
   */
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

  /**
   *
   * @param trend
   */
  private void getStatuses(Trend trend) {
    twitterLite.search(trend.getQueryString(), new Callback<List<Status>>() {
      @Override
      public void success(List<Status> statuses, Response response) {
        statusListFragment.addStatuses(statuses);
      }

      @Override
      public void failure(RetrofitError error) {
        Toast.makeText(TrendsActivity.this, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public void onTrendClick(Trend trend) {
    addStatusFragment(trend);
  }

  @Override
  public void onStatusClick(Status status) {
    Toast.makeText(this, "Status: " + status, Toast.LENGTH_SHORT).show();
  }
}
