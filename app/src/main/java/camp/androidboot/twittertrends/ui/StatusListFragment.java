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
import android.widget.ImageView;
import android.widget.TextView;
import camp.androidboot.twittertrends.R;
import camp.androidboot.twittertrends.model.Status;
import camp.androidboot.twittertrends.model.Trend;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 *
 */
public class StatusListFragment extends ListFragment implements AdapterView.OnItemClickListener {

  private static final String KEY_TREND = "trend";

  private Trend trend;
  private StatusAdapter adapter;

  public StatusListFragment() {
  }

  public static StatusListFragment newInstance(Trend trend) {
    StatusListFragment statusListFragment = new StatusListFragment();

    Bundle args = new Bundle();
    args.putString(KEY_TREND, trend.getName());
    statusListFragment.setArguments(args);

    return statusListFragment;
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    if (!(activity instanceof Callbacks)) {
      throw new IllegalStateException("Activity must implement StatusListFragment.Callbacks");
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments().containsKey(KEY_TREND)) {
      trend = new Trend(getArguments().getString(KEY_TREND));
    } else {
      throw new IllegalStateException("Fragment must have Trend argument");
    }
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    setupActionBar();
    if (adapter == null) {
      setupAdapter();
    }
    getListView().setOnItemClickListener(this);
  }

  /**
   *
   */
  private void setupActionBar() {
    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    getActivity().getActionBar().setTitle(trend.getName());
  }

  /**
   *
   */
  private void setupAdapter() {
    adapter = new StatusAdapter(getActivity());
    setListAdapter(adapter);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Status status = adapter.getItem(position);
    ((Callbacks) getActivity()).onStatusClick(status);
  }

  /**
   *
   * @param statuses
   */
  void addStatuses(List<Status> statuses) {
    adapter.addAll(statuses);
    adapter.notifyDataSetChanged();
  }

  /**
   *
   */
  private static class StatusAdapter extends ArrayAdapter<Status> {

    public StatusAdapter(Context context) {
      super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      final Status status = getItem(position);

      ViewHolder viewHolder;
      if (convertView == null) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.item_status, parent, false);
        viewHolder = new ViewHolder(convertView);
        convertView.setTag(viewHolder);
      } else {
        viewHolder = (ViewHolder) convertView.getTag();
      }

      Picasso.with(getContext()).load(status.getUserProfileImageUrl()).into(viewHolder.userProfileImage);
      viewHolder.userNameUi.setText(status.getUserName());
      viewHolder.userScreenNameUi.setText(status.getUserScreenName());
      viewHolder.statusTextUi.setText(status.getText());

      return convertView;
    }
  }

  /**
   *
   */
  private static class ViewHolder {
    private ImageView userProfileImage;
    private TextView userNameUi;
    private TextView userScreenNameUi;
    private TextView statusTextUi;

    private ViewHolder(View view) {
      userProfileImage = (ImageView) view.findViewById(R.id.user_profile_image);
      userNameUi = (TextView) view.findViewById(R.id.user_name);
      userScreenNameUi = (TextView) view.findViewById(R.id.user_screen_name);
      statusTextUi = (TextView) view.findViewById(R.id.status_text);
    }
  }

  /**
   *
   */
  interface Callbacks {
    void onStatusClick(Status status);
  }
}
