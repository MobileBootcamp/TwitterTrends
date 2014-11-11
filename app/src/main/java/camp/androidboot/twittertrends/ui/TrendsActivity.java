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
public class TrendsActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_trends);

    TwitterLite twitterLite = ((TwitterTrendsApplication) getApplication()).getTwitterLite();
    twitterLite.trends(new Callback<List<Trend>>() {
      @Override
      public void success(List<Trend> trends, Response response) {
        for (Trend trend : trends) {
          Toast.makeText(TrendsActivity.this, trend.getName(), Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void failure(RetrofitError error) {
        Toast.makeText(TrendsActivity.this, "error: " + error.toString(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
