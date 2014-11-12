package camp.androidboot.twittertrends.ui;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import camp.androidboot.twittertrends.model.Trend;
import java.util.List;

/**
 *
 */
public class TrendListFragment extends ListFragment implements AdapterView.OnItemClickListener {

  private TrendAdapter adapter;

  public TrendListFragment() {
  }

  public static TrendListFragment newInstance() {
    return new TrendListFragment();
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    adapter = new TrendAdapter(getActivity());
    setListAdapter(adapter);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    if (!(activity instanceof Callbacks)) {
      throw new IllegalStateException("Activity must implement TrendListFragment.Callbacks");
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Trend trend = adapter.getItem(position);
    ((Callbacks) getActivity()).onTrendClick(trend);
  }

  /**
   *
   * @param trends
   */
  public void addTrends(List<Trend> trends) {
    adapter.addAll(trends);
    adapter.notifyDataSetChanged();
  }

  /**
   *
   */
  private static class TrendAdapter extends ArrayAdapter<Trend> {

    public TrendAdapter(Context context) {
      super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Trend trend = getItem(position);

      if (convertView == null) {
        convertView =
            LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
      }

      TextView nameUi = (TextView) convertView.findViewById(android.R.id.text1);
      nameUi.setText(trend.getName());

      return convertView;
    }
  }

  /**
   *
   */
  public interface Callbacks {
    void onTrendClick(Trend trend);
  }
}
