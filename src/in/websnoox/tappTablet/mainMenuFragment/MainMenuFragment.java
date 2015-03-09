package in.websnoox.tappTablet.mainMenuFragment;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.OnBackPressListener;
import in.websnoox.tappTablet.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainMenuFragment extends Fragment implements OnClickListener, OnBackPressListener {
	private LayoutInflater inflater;
	private RelativeLayout takeAwayOrdersButton, takeAwayDataReportMainMEnu;
	Context context;
	private ImageView img_takeAway_mainMenu;

	@Override
	public View onCreateView(final LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.main_menu_fragment,
				container, false);

		this.inflater = inflater;
		// this.itemAddOn = itemAddOn;
		initView(view);

		return view;
	}

	private void initView(View view) {
		img_takeAway_mainMenu = (ImageView) view
				.findViewById(R.id.img_takeAway_mainMenu);
		takeAwayOrdersButton = (RelativeLayout) view
				.findViewById(R.id.takeAwayOrdersMainMEnu);
		takeAwayDataReportMainMEnu = (RelativeLayout) view
				.findViewById(R.id.takeAwayDataReportMainMEnu);
		takeAwayOrdersButton.setOnClickListener(this);
		img_takeAway_mainMenu.setOnClickListener(this);
		takeAwayDataReportMainMEnu.setOnClickListener(this);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.takeAwayOrdersMainMEnu:
			((BaseFragmentActivity) context).OnTakeAwayOrderFragmentClick();
			break;
		case R.id.img_takeAway_mainMenu:
			((BaseFragmentActivity) context).OnTakeAwayOrderFragmentClick();
			break;
		case R.id.takeAwayDataReportMainMEnu:
			((BaseFragmentActivity) context).DRTabFragment();
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onBackPressed() {
		
		return false;
	}
}
