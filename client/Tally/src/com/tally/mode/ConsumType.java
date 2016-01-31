package com.tally.mode;

import com.example.tally.R;

public class ConsumType {
	
	private static int[] backImage = new int[]{R.drawable.breakfast,R.drawable.trip,R.drawable.hotel,R.drawable.car};
	
	public static int getIndex(String type){
		if(type.equals("��ʳ"))return 0;
		if(type.equals("����"))return 1;
		if(type.equals("ס��"))return 2;
		if(type.equals("��ͨ"))return 3;
		return 0;
	}
	
	public static int getBackSource(String type) {
		return backImage[getIndex(type)];
	}
}
