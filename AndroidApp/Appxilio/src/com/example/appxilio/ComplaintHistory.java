package com.example.appxilio;

import java.io.InputStream;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Build;

public class ComplaintHistory extends Activity {

	LinearLayout layoutHistory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complaint_history);
		layoutHistory = (LinearLayout)findViewById(R.id.LinearLayoutHistory);
		RenderMyMessage();
		RenderMyMessage();
		RenderMyMessage();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.complaint_history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@SuppressLint("NewApi")
	public void RenderMyMessage()
	{
		LinearLayout layoutmessage = new LinearLayout(this);
		layoutmessage.setOrientation(LinearLayout.HORIZONTAL);
		layoutmessage.setBackground(getResources().getDrawable(R.drawable.stylelinearlayout));		
		SetearImagen(layoutmessage, 1);
		TextView Text = new TextView(this);
		Text.setText("yeahhhh ajaa ajaja");
		Text.setGravity(Gravity.LEFT);
		Text.setTextSize(12);
		Text.setPaintFlags(Text.getPaintFlags()|Paint.FAKE_BOLD_TEXT_FLAG);
		layoutmessage.addView(Text);		
		layoutHistory.addView(layoutmessage);
		int left = 2;
		int top = 20;
		int right = 2;
		int bottom = 2;
		TableRow.LayoutParams params = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(left, top, right, bottom);
		layoutmessage.setLayoutParams(params);
		
		
		
	}
	
	public void SetearImagen(LinearLayout layout, int image)
	{
		try{
			
			ImageView myImageView = new ImageView(this);
			myImageView.setPadding(0, 7, 0, 0);
			myImageView.setImageDrawable(getResources().getDrawable(R.drawable.police_full));	
			layout.addView(myImageView);
			android.view.ViewGroup.LayoutParams layoutParams = myImageView.getLayoutParams();
			layoutParams.width = 150;
			layoutParams .height = 160;
			myImageView.setLayoutParams(layoutParams);
		}catch(Exception ex)
		{
			
		}
	}

	

}
