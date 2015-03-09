package in.websnoox.tappTablet.dataReport;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.dataReport.adapter.DataReportOrderAdapter;
import in.websnoox.tappTablet.dataReport.entity.OrderItem;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DROrderFragment extends Fragment {

	private Context context;
	private View view;
	ListView listview_OR;
	DataReportOrderAdapter orderAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.data_report_order_layout, container,
				false);
		init(view);
		return view;
	}

	private void init(View v) {

		listview_OR = (ListView) view.findViewById(R.id.listview_OR);
		orderAdapter = new DataReportOrderAdapter(context);
		listview_OR.setAdapter(orderAdapter);
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				showOrder();
			}
		}, 100);
	}

	protected void showOrder() {

		for (int i = 0; i < 5; i++) {

       orderAdapter.addDataSet(new OrderItem("Japanese Char Siu Noodle", "1","7.50"));			
		}
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context=activity;
	}

}
