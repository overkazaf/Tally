package com.tally.activity;

import com.example.tally.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Test2Activity extends Activity {
	
	private Button testingButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_test);
		
		testingButton = (Button) findViewById(R.id.button1);
	}
}

