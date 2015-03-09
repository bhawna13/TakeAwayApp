package in.websnoox.tappTablet.orderFragment;

import in.websnoox.tappTablet.BaseFragmentActivity;
import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.entity.Order;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

public class NewOrderReceiptLayout extends BaseAdapter{

	private Context context;
	private ArrayList<Order> items= new ArrayList<Order>();
	String frmWhr;
	private ViewHolder holder;
	UpdateViews views;
	public NewOrderReceiptLayout(Context context, ArrayList<Order> items, String fromwhere, OrderFragment orderFragment) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.items = items;
		this.frmWhr=fromwhere;
		views=orderFragment;

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
	public View getView(final int position, View convertView, final ViewGroup parent) {


		if (convertView == null) {
			holder = new ViewHolder();

			convertView = LayoutInflater.from(context).inflate(
					R.layout.new_order_recipt_layout, parent,false);
			holder.bottomLayout=(RelativeLayout)convertView.findViewById(R.id.relative_bottom);
			holder.bottomLayout.setVisibility(View.GONE);
			holder.transparentLayout = (RelativeLayout)
					convertView.findViewById(R.id.transparentwhite_orderReceipt);
			holder.layoutChild = (RelativeLayout)convertView.findViewById(R.id.relative_mainOrderReceipt);
			holder.topheader = (View)convertView.findViewById(R.id.topViewOrderReceipt);
			holder.upperPart = (RelativeLayout)convertView.findViewById(R.id.relative_upperPart);
			holder.relativeChild = (RelativeLayout)convertView.findViewById(R.id.childerelative);
			
			
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(items.get(position).isSelectedToDelete)
			holder.transparentLayout.setVisibility(View.VISIBLE);
		else
			holder.transparentLayout.setVisibility(View.INVISIBLE);
		
		//disableTouchTheft(holder.relativeChild);
		holder.upperPart.setBackgroundColor(context.getResources().getColor(R.color.purple_OrderStatus_selected));
		
		
		holder.relativeChild.setOnClickListener(Onclick(holder,position));

		return convertView;
	}

	private OnClickListener Onclick(final ViewHolder holder2,final int position) {
		// TODO Auto-generated method stub
		return new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				((BaseFragmentActivity) context).OnIndiviDualReciptTwiceClick(arg0);
				/*for(int i=0;i<items.size();i++){
					if(i==position){
						items.get(i).isSelectedToDelete=true;
					}
					else{
						items.get(i).isSelectedToDelete=false;
					}
				}
				
				views.UpdatehorizontalViews(items);*/
			}
		};
	}

	static class ViewHolder {
		RelativeLayout bottomLayout,layoutChild,relativeChild;	
		RelativeLayout transparentLayout,upperPart;
		View topheader;


	}
	int childPosition;
	public void setClickedChildPosition(int clickedPosition){
		this.childPosition=clickedPosition;

	}
	
	
	public static void disableTouchTheft(View view) {
	    view.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View view, MotionEvent motionEvent) {
	            view.getParent().requestDisallowInterceptTouchEvent(true);
	            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
	            case MotionEvent.ACTION_UP:
	                view.getParent().requestDisallowInterceptTouchEvent(false);
	                break;
	            }
	            return false;
	        }
	    });
	}

}
