package in.websnoox.tappTablet.dataReport.adapter;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.adapter.BaseListAdapter;
import in.websnoox.tappTablet.dataReport.entity.ItemisedDataReportItem;
import in.websnoox.tappTablet.ui.CustomTextView;
import in.websnoox.tappTablet.util.Util;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class DataReportItemisedAdapter<T> extends BaseListAdapter<T> {

	public DataReportItemisedAdapter(Context context) {
		super(context);
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DataReportItemisedHolder holder;

		if (convertView == null) {
			holder = new DataReportItemisedHolder();
			convertView = inflater.inflate(R.layout.data_report_row_itemised,
					null);
			holder.tvName = (CustomTextView) convertView
					.findViewById(R.id.tvName_drItemised);
			holder.tvQuantity = (CustomTextView) convertView
					.findViewById(R.id.tvQuantity_drItemised);
			holder.tvAmount = (CustomTextView) convertView
					.findViewById(R.id.tvAmount_drItemised);
			convertView.setTag(holder);

		} else
			holder = (DataReportItemisedHolder) convertView.getTag();

		ItemisedDataReportItem item = (ItemisedDataReportItem) dataSetItems
				.get(position);

		holder.tvName.setText(item.getName());
		holder.tvQuantity.setText(item.getQuantity());
		holder.tvAmount.setText("£ "+item.getAmount());

		return convertView;
	}
	
	public String getTotalAmount() {

		float totalAmount = 0.00f;
		int noOfRows = getCount();
		for (int i = 0; i < noOfRows; i++) {
			ItemisedDataReportItem item = (ItemisedDataReportItem) dataSetItems.get(i);
			totalAmount = totalAmount + Float.valueOf(item.getAmount());
		}

		return Util.numberfornmat(totalAmount);
	}
	public class DataReportItemisedHolder {

		public CustomTextView tvName, tvQuantity, tvAmount;

	}

}
