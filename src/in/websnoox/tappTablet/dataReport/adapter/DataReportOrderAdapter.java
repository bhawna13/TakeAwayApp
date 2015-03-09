package in.websnoox.tappTablet.dataReport.adapter;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.adapter.BaseListAdapter;
import in.websnoox.tappTablet.dataReport.entity.OrderItem;
import in.websnoox.tappTablet.ui.CustomTextView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DataReportOrderAdapter extends BaseListAdapter<OrderItem> {

	public DataReportOrderAdapter(Context c) {
		super(c);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		OrderViewHolder holder;
		if (convertView == null) {
			holder = new OrderViewHolder();
			convertView = inflater
					.inflate(R.layout.order_item_row_layout, null);
			holder.ll_order_item_row=(LinearLayout)convertView.findViewById(R.id.ll_order_item_row);
			holder.tvRelSelected_drOR = (RelativeLayout) convertView
					.findViewById(R.id.tvRelSelected_drOR);
			holder.tvSelected_drOR = (ImageView) convertView
					.findViewById(R.id.tvSelected_drOR);
			holder.tvName_drOR = (CustomTextView) convertView
					.findViewById(R.id.tvName_drOR);
			holder.tvQuantity_drOR = (CustomTextView) convertView
					.findViewById(R.id.tvQuantity_drOR);
			holder.tvPrice_drOR = (CustomTextView) convertView
					.findViewById(R.id.tvPrice_drOR);
			
			convertView.setTag(holder);

		} else
			holder = (OrderViewHolder) convertView.getTag();

		OrderItem orderItem = (OrderItem) dataSetItems.get(position);
		holder.tvName_drOR.setText(orderItem.getName());
		holder.tvQuantity_drOR.setText(orderItem.getQuantity());
		holder.tvPrice_drOR.setText("£ " +orderItem.getPrice());

		if (isSelectionEnable())
			holder.tvRelSelected_drOR.setVisibility(View.VISIBLE);
		else
			holder.tvRelSelected_drOR.setVisibility(View.GONE);

		if (orderItem.isSelected())
			holder.tvSelected_drOR.setSelected(true);
		else
			holder.tvSelected_drOR.setSelected(false);
		
		if(position%2==0)
			holder.ll_order_item_row.setBackgroundColor(context.getResources().getColor(R.color.light_grey));
		else
			holder.ll_order_item_row.setBackgroundColor(context.getResources().getColor(R.color.white_dr_background));
		
		holder.tvRelSelected_drOR.setTag(holder);
		holder.tvSelected_drOR.setTag(holder);
		holder.tvName_drOR.setTag(holder);
		holder.tvQuantity_drOR.setTag(holder);
		holder.tvPrice_drOR.setTag(holder);

		return convertView;
	}

	public class OrderViewHolder {

		public LinearLayout ll_order_item_row;
		public RelativeLayout tvRelSelected_drOR;
		public ImageView tvSelected_drOR;
		public CustomTextView tvName_drOR, tvQuantity_drOR, tvPrice_drOR;
	}

}
