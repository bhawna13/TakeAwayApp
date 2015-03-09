package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.dataReport.wheel.ABWheel;
import in.websnoox.tappTablet.dataReport.wheel.OnWheelChangedListener;
import in.websnoox.tappTablet.dataReport.wheel.OnWheelScrollListener;
import in.websnoox.tappTablet.dataReport.wheelAdapter.ABArrayWheelAdapter;
import in.websnoox.tappTablet.dataReport.wheelAdapter.ABNumericWheelAdapter;
import in.websnoox.tappTablet.util.Logger;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DRDateFragment extends Fragment {

	private Context context;
	private View view;

	protected int curMonth = 0, curDay = 0;
	protected int startTotalDays = 0, endTotalDays = 0;

	protected ABWheel startMonths, startDays, endMonths, endDays;

	protected boolean isscrollFinish = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.data_report_date_layout, container,
				false);
		init(view);
		return view;
	}

	private void init(View v) {
		startMonths = (ABWheel) v.findViewById(R.id.startMonths);
		startDays = (ABWheel) v.findViewById(R.id.startDays);

		endMonths = (ABWheel) v.findViewById(R.id.endMonths);
		final ABWheel endDays = (ABWheel) v.findViewById(R.id.endDays);

		Calendar calendar = Calendar.getInstance();

		// startMonth
		curMonth = calendar.get(Calendar.MONTH);

		Logger.d("curHour", "::" + curMonth);

		ABArrayWheelAdapter<String> starthourAdapter = new ABArrayWheelAdapter<>(
				context, getResources().getStringArray(R.array.months_array));

		startMonths.setViewAdapter(starthourAdapter);
		startMonths.setCurrentItem(curMonth);
		startMonths.setCyclic(true);
		startMonths.setVisibleItems(14);
		startMonths.addChangingListener(OnMonthsChangeListener());
		startMonths.addScrollingListener(onScrollListener());

		// startDay
		curDay = calendar.get(Calendar.DAY_OF_WEEK);
		startTotalDays =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Logger.d("curDay", "::" + curDay);

		ABNumericWheelAdapter startminAdapter = new ABNumericWheelAdapter(
				context, 1, startTotalDays, "%02d");

		startDays.setViewAdapter(startminAdapter);
		startDays.setCyclic(true);
		startDays.setCurrentItem(curDay);
		startDays.setVisibleItems(14);

		ABArrayWheelAdapter<String> endhourAdapter = new ABArrayWheelAdapter<>(
				context, getResources().getStringArray(R.array.months_array));

		endMonths.setViewAdapter(endhourAdapter);
		endMonths.setCurrentItem(curMonth);
		endMonths.setCyclic(true);
		endMonths.setVisibleItems(14);

		// startMin
		curDay = calendar.get(Calendar.DAY_OF_WEEK);
		Logger.d("curDay", "::" + curDay);

		ABNumericWheelAdapter endminAdapter = new ABNumericWheelAdapter(
				context, 1, startTotalDays, "%02d");

		endDays.setViewAdapter(endminAdapter);
		endDays.setCyclic(true);
		endDays.setCurrentItem(curDay);
		endDays.setVisibleItems(14);

	}

	private OnWheelScrollListener onScrollListener() {
		// TODO Auto-generated method stub
		return new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(ABWheel wheel) {

				isscrollFinish = false;

			}

			@Override
			public void onScrollingFinished(ABWheel wheel) {
				isscrollFinish = true;
				Logger.i("::onScrollingFinished", "::onScrollingFinished");
				Logger.i("::startMonths.getCurrentItem()","::"+startMonths.getCurrentItem());
				
				Calendar calendar = Calendar.getInstance();
		        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		        calendar.set(Calendar.MONTH, startMonths.getCurrentItem());
		        
				startTotalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				Logger.i("::", "startTotalDays::" + startTotalDays);
				startDays.setViewAdapter(new ABNumericWheelAdapter(context,
						1, startTotalDays, "%02d"));
				startDays.setCyclic(true);
				//startDays.setCurrentItem(curDay);
			}
		};
	}

	private OnWheelChangedListener OnMonthsChangeListener() {
		// TODO Auto-generated method stub
		return new OnWheelChangedListener() {

			@Override
			public void onChanged(ABWheel wheel, int oldValue, int newValue) {

				
				
				if (isscrollFinish) {
					
				}
			}
		};
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.context = activity;
	}

}
