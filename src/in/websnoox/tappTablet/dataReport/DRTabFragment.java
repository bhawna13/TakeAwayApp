package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.OnBackPressListener;
import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.constants.StaticConstants;
import in.websnoox.tappTablet.ui.CustomTextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DRTabFragment extends Fragment implements OnClickListener, OnBackPressListener {

	private Context context;
	private View view;
	private CustomTextView txtTodayDate;

	ImageView imgToday, imgSearch, imgControlPanel;
	RelativeLayout relToday, relSearch, relControlPanel;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.datareport_main_fragment, container,
				false);

		relToday = (RelativeLayout) view.findViewById(R.id.relToday);
		txtTodayDate = (CustomTextView) view.findViewById(R.id.txtTodayDate);
		imgToday = (ImageView) view.findViewById(R.id.imgToday);
		relToday.setOnTouchListener(onTouchLister(1));
		relToday.setOnClickListener(this);

		relSearch = (RelativeLayout) view.findViewById(R.id.relSearch);
		imgSearch = (ImageView) view.findViewById(R.id.imgSearch);
		relSearch.setOnTouchListener(onTouchLister(2));
		relSearch.setOnClickListener(this);

		relControlPanel = (RelativeLayout) view
				.findViewById(R.id.relControlPanel);
		imgControlPanel = (ImageView) view.findViewById(R.id.imgControlPanel);
		relControlPanel.setOnTouchListener(onTouchLister(3));
		relControlPanel.setOnClickListener(this);

		return view;
	}

	private OnTouchListener onTouchLister(final int position) {
		return new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {

				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN:
					onTouchEventDOWN(position);
					break;

				case MotionEvent.ACTION_UP:
					onTouchEventUP(position);
					break;

				}
				return false;

			}
		};
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.relToday:
			
			Bundle bundle = new Bundle();
			bundle.putInt(StaticConstants.KEY_DATA_REPORT_ARG,0);
			bundle.putBoolean(StaticConstants.KEY_DATA_REPORT_SERCH_ARG, false);
			((BaseFragmentActivity) context).DRBaseFragment(bundle);
			
			break;

		case R.id.relSearch:
			// ((MainActivity) context).OnDataReportBaseFragmentClick(-1, true);
			((BaseFragmentActivity) context).oncreateDataReprotSearchFragment();
			break;

		case R.id.relControlPanel:
			// txtDate_dr.performClick();
			break;

		default:
			break;
		}

	}

	@SuppressLint("NewApi")
	public void onTouchEventDOWN(int position) {

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		float alpha = 1f;

		switch (position) {
		case 1:

			imgToday.setAlpha(alpha);
			imgToday.setLayoutParams(params);
			txtTodayDate.setAlpha(alpha);
			break;

		case 2:

			imgSearch.setAlpha(alpha);
			imgSearch.setLayoutParams(params);
			break;

		case 3:
			imgControlPanel.setAlpha(alpha);
			imgControlPanel.setLayoutParams(params);
			break;

		default:
			break;
		}
	}

	@SuppressLint("NewApi")
	public void onTouchEventUP(int position) {

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.setMargins(40, 40, 40, 40);
		float alpha = 0.5f;

		switch (position) {
		case 1:
			imgToday.setAlpha(alpha);
			imgToday.setLayoutParams(params);
			txtTodayDate.setAlpha(alpha);
			break;

		case 2:

			imgSearch.setAlpha(alpha);
			imgSearch.setLayoutParams(params);
			break;

		case 3:
			imgControlPanel.setAlpha(alpha);
			imgControlPanel.setLayoutParams(params);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onBackPressed() {
		((BaseFragmentActivity)context).Selectfragment(0);
		return true;
	}

}
