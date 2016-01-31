package com.tally.views;

import org.json.JSONObject;

import com.example.tally.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tally.activity.MainActivity;
import com.tally.entity.ConsumptionEntity;
import com.tally.helper.TallyHttpClient;
import com.tally.mode.ConsumType;

import cz.msebera.android.httpclient.Header;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 对应了DetailView上一个个具体的消费记录
 *
 */
public class DetailItemView extends LinearLayout {
	
	public ImageView costTypeImageView;
	public TextView costNameTextView;
	public TextView costTextView;
	public String location;
	public String costType;
	private static LinearLayout popView;
	private static LinearLayout itemView;
	private static PopupWindow pw;              //点击more按钮弹出菜单
	public static Integer currentItem;
	
	public DetailItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		itemView = (LinearLayout) inflate(context, R.layout.detail_item, null);
		addView(itemView);
		
		costTypeImageView = (ImageView)findViewById(R.id.id_imgtype);
		costNameTextView = (TextView) findViewById(R.id.cost_text);
		costTextView = (TextView) findViewById(R.id.id_cosumname);
		popView = (LinearLayout) inflate(context, R.layout.detail_pop, null);
		pw = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		
		
		ImageButton morebtn = (ImageButton)findViewById(R.id.morebtn);
	    morebtn.setOnClickListener(new OnClickListener() {
			/**
			 * 设置more按钮的点击事件
			 */
			@Override
			public void onClick(View view) {
				currentItem = (Integer)view.getTag();
				showPopupWindow(view,currentItem);
			}
	    });
	}
	private void showPopupWindow(View  view,final Integer index){		
		pw.setTouchable(true);
		pw.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.menuback));
		pw.showAsDropDown(view);
		
		
		popView.findViewById(R.id.edit_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 点击more菜单中的编辑按钮
				ConsumptionEntity consumptionEntity = DetailView.costlist.get(index);
				DetailEditView.consumeNameEditText.setText(consumptionEntity.ConsumName);
				DetailEditView.consumeCostEditText.setText(consumptionEntity.Cost);
				DetailEditView.consumeLocationEditText.setText(consumptionEntity.Location);
				DetailEditView.consumeTypeSpinner.setSelection(ConsumType.getIndex(consumptionEntity.ConsumType));
				pw.dismiss();
				DetailEditView.submitButton.setText("更新");
				DetailView.showPopWind();
			}
		});
		
		popView.findViewById(R.id.delete_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				pw.dismiss();
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getContext()).setTitle("系统提示")//设置对话框标题  
				  
			     .setMessage("是否确定删除该信息！")//设置显示的内容  
			  
			     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
			         @Override  
			         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件 
			        	 RequestParams params = new RequestParams();
			        	 params.add("Id", DetailView.costlist.get(currentItem).getId());
			        	 TallyHttpClient.get("deleteconsume", params, new JsonHttpResponseHandler(){
			        		@Override
			        		public void onSuccess(int statusCode,
			        				Header[] headers, JSONObject response) {
			        			// TODO Auto-generated method stub
			        			try {
									if (statusCode == 200
											&& response != null
											&& response.get("status").equals("success"))
										Toast.makeText(getContext(), "删除成功",Toast.LENGTH_LONG).show();
									MainActivity.freshView();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
			        			super.onSuccess(statusCode, headers, response);
			        		} 
			        	 });
			         }  
			  
			     }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮  
			         @Override  
			         public void onClick(DialogInterface dialog, int which) {//响应事件
			        	 
			         }  
			  
			     }).show();//在按键响应事件中显示此对话框
			}
		});
		
		
	}
}
