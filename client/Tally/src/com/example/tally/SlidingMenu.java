package com.example.tally;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;

	private int mMenuWidth;
	// dp
	private int mMenuRightPadding = 50;

	private boolean once;

	private boolean isOpen;
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		
		//��ȡ��Ļ�Ŀ��
		WindowManager wManager =(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wManager.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		
		//��dpת����px
		mMenuRightPadding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMenuRightPadding, context.getResources().getDisplayMetrics());

	}
	
	//������View�Ŀ�͸�
	//�����Լ��Ŀ�͸�
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if(!once){
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			mWapper.getLayoutParams().width = mScreenWidth;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	//ͨ������ƫ��������menu����
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
		
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollBy(mMenuWidth, 0);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if(scrollX > mMenuWidth/2){
				this.smoothScrollTo(mMenuWidth, 0);
			}
			else {
				this.smoothScrollTo(0, 0);
			}
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
}
