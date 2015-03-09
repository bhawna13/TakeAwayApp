package in.websnoox.tappTablet.notificationFragment;


import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.entity.Order;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewOrdersAdapter extends BaseAdapter{


	private Context context;
	private ArrayList<Order> items= new ArrayList<Order>();
	String frmWhr;

	public NewOrdersAdapter(Context context, ArrayList<Order> items, String fromwhere) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.items = items;
		this.frmWhr=fromwhere;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			holder = new ViewHolder();

			convertView = LayoutInflater.from(context).inflate(
					R.layout.new_orders_notification_layout, parent,false);
			holder.OrderId= (TextView) convertView
					.findViewById(R.id.orderIDlistViewItem);
			holder.OrderTime= (TextView) convertView
					.findViewById(R.id.orderTimelistViewItem);
			holder.Username= (TextView) convertView
					.findViewById(R.id.CutomerNamelistViewItem);
			holder.relative_background=(RelativeLayout)convertView
					.findViewById(R.id.relative_newOrderItemsList);
			holder.img_radioButton=(ImageView)convertView
					.findViewById(R.id.img_radioButton);
			holder.img_timerView=(ImageView)convertView
					.findViewById(R.id.img_timerView);
			holder.img_arrow=(ImageView)convertView
					.findViewById(R.id.img_arrow);
			convertView.setTag(holder);

		}
		ViewHolder hold = (ViewHolder) convertView.getTag();
		if(frmWhr.equalsIgnoreCase("withoutDelete"))
		{
			hold.img_radioButton.setVisibility(View.VISIBLE);
			hold.img_timerView.setVisibility(View.VISIBLE);
		}
		else{
			hold.img_radioButton.setVisibility(View.GONE);
			hold.img_timerView.setVisibility(View.GONE);
		}

		if(items.get(position).isSelectedToDelete)
			hold.img_radioButton.setBackgroundDrawable((context.getResources().
					getDrawable(R.drawable.checkbox_selected)));
		else
			hold.img_radioButton.setBackgroundDrawable((context.getResources().
					getDrawable(R.drawable.checkbox_unselected)));	

		if(position%2==0){
			hold.relative_background.setBackgroundColor
			(context.getResources().getColor(R.color.greyLight_listItem));
			hold.img_arrow.setBackgroundDrawable((context.getResources().
					getDrawable(R.drawable.arrow)));
		}
		else{
			hold.relative_background.setBackgroundColor
			(context.getResources().getColor(R.color.white));
			hold.img_arrow.setBackgroundDrawable((context.getResources().
					getDrawable(R.drawable.arrow_highlight)));
		}


		if(childPosition==position)
			hold.relative_background.setBackgroundColor
			(context.getResources().getColor(R.color.greySelected_listItem));

		hold.OrderId.setText(String.valueOf(items.get(position).orderId));
		hold.OrderTime.setText(items.get(position).OrderTime);
		hold.Username.setText(items.get(position).Username);

		return convertView;
	}

	static class ViewHolder {
		TextView Username, OrderId, OrderTime;
		RelativeLayout relative_background;
		ImageView img_radioButton,img_timerView,img_arrow;

	}
	int childPosition;
	public void setClickedChildPosition(int clickedPosition){
		this.childPosition=clickedPosition;

	}
}
