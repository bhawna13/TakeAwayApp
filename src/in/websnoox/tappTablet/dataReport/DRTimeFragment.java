package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.constants.StaticConstants;
import in.websnoox.tappTablet.dataReport.wheel.ABWheel;
import in.websnoox.tappTablet.dataReport.wheelAdapter.ABNumericWheelAdapter;
import in.websnoox.tappTablet.ui.CustomTextView;
import in.websnoox.tappTablet.util.Logger;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class DRTimeFragment extends Fragment implements OnClickListener {

	private Context context;
	private View view;

	protected int curHour = 0;
	protected int curMin = 0;
	
	public int KEY_DATA_REPORT_ARG = -1;
	public boolean KEY_DATA_REPORT_SEARCH_ARG = false;
	
	
	ABWheel startHour, startMin, endHour, endMin;
	CustomTextView txtokTime, txtCancelTime;
	Bundle bundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		KEY_DATA_REPORT_ARG = getArguments().getInt(
				StaticConstants.KEY_DATA_REPORT_ARG);
		KEY_DATA_REPORT_SEARCH_ARG = getArguments().getBoolean(
				StaticConstants.KEY_DATA_REPORT_SERCH_ARG);
		view = inflater.inflate(R.layout.data_report_time_layout, container,
				false);
		init(view);
		return view;
	}

	private void init(View v) {

		txtokTime = (CustomTextView) v.findViewById(R.id.txtokTime);
		txtokTime.setOnClickListener(this);
		txtCancelTime = (CustomTextView) v.findViewById(R.id.txtCancelTime);
		txtCancelTime.setOnClickListener(this);

		startHour = (ABWheel) v.findViewById(R.id.startHour);
		startMin = (ABWheel) v.findViewById(R.id.startMin);

		endHour = (ABWheel) v.findViewById(R.id.endHour);
		endMin = (ABWheel) v.findViewById(R.id.endMin);

		Calendar calendar = Calendar.getInstance();

		// startHour
		curHour = calendar.get(Calendar.HOUR_OF_DAY);
		Logger.d("curHour", "::" + curHour);

		ABNumericWheelAdapter starthourAdapter = new ABNumericWheelAdapter(
				context, 0, 23);

		startHour.setViewAdapter(starthourAdapter);
		startHour.setCurrentItem(curHour);
		startHour.setCyclic(true);
		startHour.setVisibleItems(14);

		// startMin
		curMin = calendar.get(Calendar.MINUTE);
		Logger.d("curMin", "::" + curMin);

		ABNumericWheelAdapter startminAdapter = new ABNumericWheelAdapter(
				context, 0, 59, "%02d");

		startMin.setViewAdapter(startminAdapter);
		startMin.setCyclic(true);
		startMin.setCurrentItem(curMin);
		startMin.setVisibleItems(14);

		ABNumericWheelAdapter endhourAdapter = new ABNumericWheelAdapter(
				context, 0, 23);

		endHour.setViewAdapter(endhourAdapter);
		endHour.setCurrentItem(curHour);
		endHour.setCyclic(true);
		endHour.setVisibleItems(14);

		// startMin
		curMin = calendar.get(Calendar.MINUTE);
		Logger.d("curMin", "::" + curMin);

		ABNumericWheelAdapter endminAdapter = new ABNumericWheelAdapter(
				context, 0, 59, "%02d");

		endMin.setViewAdapter(endminAdapter);
		endMin.setCyclic(true);
		endMin.setCurrentItem(curMin);
		endMin.setVisibleItems(14);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.context = activity;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.txtokTime:
			bundle = new Bundle();
			bundle.putInt(StaticConstants.KEY_DATA_REPORT_ARG, KEY_DATA_REPORT_ARG);
			bundle.putBoolean(StaticConstants.KEY_DATA_REPORT_SERCH_ARG, KEY_DATA_REPORT_SEARCH_ARG);
			bundle.putString( StaticConstants.KEY_START_TIME, startHour.getCurrentItem() + ":" + startMin.getCurrentItem());
			bundle.putString(StaticConstants.KEY_END_TIME, endHour.getCurrentItem() + ":" + endMin.getCurrentItem());
		//	((BaseFragmentActivity) context).DRBaseFragment(bundle);
			
			showTimeValue(bundle);
			break;
			
		case R.id.txtCancelTime:
			bundle = new Bundle();
			bundle.putInt(StaticConstants.KEY_DATA_REPORT_ARG, KEY_DATA_REPORT_ARG);
			bundle.putBoolean(StaticConstants.KEY_DATA_REPORT_SERCH_ARG, KEY_DATA_REPORT_SEARCH_ARG);
		//	bundle.putString( StaticConstants.KEY_START_TIME, startHour.getCurrentItem() + ":" + startMin.getCurrentItem());
		//	bundle.putString(StaticConstants.KEY_END_TIME, endHour.getCurrentItem() + ":" + endMin.getCurrentItem());
		//	((BaseFragmentActivity) context).DRBaseFragment(bundle);
			
			showTimeValue(bundle);
			break;

		default:
			break;
		}

	}

	
	
	public void showTimeValue(Bundle bundle)
	{
		Fragment fragment = getFragmentManager().findFragmentById(
				R.id.base_container);
		if (fragment.getTag().equalsIgnoreCase(
				StaticConstants.DATA_REPORT_BASE_TAG)) {
			DRBaseFragment baseFragment = (DRBaseFragment) fragment;
			baseFragment.CurrentTimeBundle(bundle);
		}
	}

}
