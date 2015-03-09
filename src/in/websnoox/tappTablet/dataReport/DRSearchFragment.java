package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.dataReport.calender.CaldroidFragment;
import in.websnoox.tappTablet.dataReport.calender.CaldroidListener;
import in.websnoox.tappTablet.ui.CustomTextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DRSearchFragment extends Fragment {

	private View view;
	private Context context;
	private CaldroidFragment caldroidFragment;
    private CustomTextView startDate,endDate;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.data_report_search_fragment,
				container, false);
		init(view);
		return view;
	}

	private void init(View v) {

		startDate=(CustomTextView)v.findViewById(R.id.startDate);
		endDate=(CustomTextView)v.findViewById(R.id.endDate);
		
		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

		caldroidFragment = new CaldroidFragment();
		// setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();

		// Setup listener
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Toast.makeText(context, formatter.format(date),
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onChangeMonth(int month, int year) {
				String text = "month: " + month + " year: " + year;
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLongClickDate(Date date, View view) {
				Toast.makeText(context, "Long click " + formatter.format(date),
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCaldroidViewCreated() {
				if (caldroidFragment.getLeftArrowButton() != null) {
					Toast.makeText(context, "Caldroid view is created",
							Toast.LENGTH_SHORT).show();
				}
			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
	}

}
