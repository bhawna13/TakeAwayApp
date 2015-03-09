package in.websnoox.tappTablet.notificationFragment;

import in.websnoox.tappTablet.R;
import in.websnoox.tappTablet.entity.Order;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MyPagerAdapter extends PagerAdapter{

	private Context context;
	private ArrayList<Order> newOrderitemlist=new ArrayList<>();
	private ListView firstListViewPager;
	private ListView secondListViewPager;
	private NewOrdersAdapter adapter_newOrdersPager;
	UpdatePagerAdapter updateadapter;


	public MyPagerAdapter(Context context, ArrayList<Order> newitemlist,NotificationFragment fragment) {
		this.context = context;
		this.newOrderitemlist=newitemlist;
		updateadapter=fragment;
	}
	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_base_layout, container,
				false);

		firstListViewPager=(ListView)itemView.
				findViewById(R.id.firstListOfNewOrder);
		secondListViewPager=(ListView)itemView.
				findViewById(R.id.secondListOfNewOrder);
		adapter_newOrdersPager = new NewOrdersAdapter(context, newOrderitemlist,"withoutDelete");
		firstListViewPager.setAdapter(adapter_newOrdersPager);
		secondListViewPager.setAdapter(adapter_newOrdersPager);
		firstListViewPager.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				newOrderitemlist.get(arg2).isSelectedToDelete=true;
				adapter_newOrdersPager.notifyDataSetChanged();
				updateadapter.updatePager();
				//setAdapter(myPagerAdapter);
			}
		});
		secondListViewPager.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				newOrderitemlist.get(arg2).isSelectedToDelete=true;
				adapter_newOrdersPager.notifyDataSetChanged();
				updateadapter.updatePager();
				//viewPager.setAdapter(myPagerAdapter);
			}
		});


		//adapterInstance(adapter_newOrdersPager);

		((ViewPager) container).addView(itemView);
		return itemView;
	}
	public void notifyAdapter(){
		adapter_newOrdersPager.notifyDataSetChanged();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((LinearLayout) object);
	}

}
