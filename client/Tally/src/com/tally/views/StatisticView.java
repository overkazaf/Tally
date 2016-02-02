package com.tally.views;

import java.util.ArrayList;

import org.json.JSONArray;

import com.example.tally.R;
import com.tally.helper.TallyHttpClient;
import com.tally.mode.BaseView;
import com.tally.mode.TJsonHttpResponseHandler;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class StatisticView extends BaseView{
	private static LineChart chart;
	private static DatePickerDialog datePickerDialog;
	private static Integer currentDate;
	private static TextView startDtTV;
	private static TextView endDtTV;
	private static Spinner costType;
	private static RelativeLayout statisticView;
	public StatisticView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		statisticView = (RelativeLayout)inflate(context, R.layout.activity_statistic, null);
		addView(statisticView);
		final LinearLayout startDateLL = (LinearLayout)findViewById(R.id.line_st);
		final LinearLayout endDateLL = (LinearLayout)findViewById(R.id.line_et);
		startDtTV = (TextView)findViewById(R.id.stdt_tv);
		endDtTV = (TextView)findViewById(R.id.eddt_tv);
		costType = (Spinner)findViewById(R.id.typespinner);
		costType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				RefreshView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		datePickerDialog = new DatePickerDialog(context, null, 2016, 2, 2);
		datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "确定",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				int year = datePickerDialog.getDatePicker().getYear();
				int month = datePickerDialog.getDatePicker().getMonth();
				int day = datePickerDialog.getDatePicker().getDayOfMonth();
				if(currentDate == 0){
					startDtTV.setText(String.format("%d-%d-%d", year,month+1,day));
				}else {
					endDtTV.setText(String.format("%d-%d-%d", year,month+1,day));
				}
			}
		});
		
		startDateLL.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				currentDate = 0;
				datePickerDialog.show();
			}
		});
		
		endDateLL.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				currentDate = 1;
				datePickerDialog.show();
			}
		});
		
		chart = (LineChart)findViewById(R.id.chart);
		chart.getAxisLeft().setStartAtZero(false);
		chart.getAxisRight().setEnabled(false);
		
		chart.setDescription("Tally统计");
	}
	
	public void RefreshView()
	{
		
		
	    RequestParams params = new RequestParams();
	    String beginDateStr = startDtTV.getText().toString();
	    String endDateStr = endDtTV.getText().toString();
	    final String csType = costType.getSelectedItem().toString();
	    if(!beginDateStr.equals("开始日期")&&!endDateStr.equals("结束日期")){
	    	params.add("userId","0001");
	    	params.add("consumeType", csType);
		    params.add("beginDate", beginDateStr);
		    params.add("endDate", endDateStr);
		    
		    TallyHttpClient.get("getstatistic", params, new TJsonHttpResponseHandler(){
		    	
		    	@Override
		    	public void onSuccess(int statusCode, Header[] headers,
		    			JSONArray response) {
		    		if(statusCode==200 && response != null){
		    			ArrayList<String> datelist = new ArrayList<String>();
		    			ArrayList<LineDataSet> ydata = new ArrayList<LineDataSet>();
		    		    ArrayList<Entry> dataList = new ArrayList<Entry>();
		    		    float maxVale = 0;
		    		    try {
		    		    	
		    		    	for(int i = 0;i<response.length();i++){
			    				datelist.add(response.getJSONObject(i).getString("MDate"));
			    				float val = Float.parseFloat(response.getJSONObject(i).getString("Cost"));
			    				maxVale = maxVale<val?val:maxVale;
			    				dataList.add(new Entry(val, i));
			    			}
						} catch (Exception e) {
							e.printStackTrace();
						}
		    			ydata.add(new LineDataSet(dataList, csType));
		    			LineData data  = new LineData(datelist,ydata);
		    			chart.getAxisLeft().setAxisMaxValue(maxVale+10);
		    			chart.setData(data);
		    			chart.invalidate();
		    		}
		    		super.onSuccess(statusCode, headers, response);
		    	}
		    });
	    }
	}
}
