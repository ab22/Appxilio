package com.example.appxilio;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import android.provider.Settings;

public class MainActivity extends Activity {

	
	boolean isSpeakButtonLongPressed = false;
	ImageView police;
	ImageView firefighter;
	ImageView ambulance;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean enabled = service
		  .isProviderEnabled(LocationManager.GPS_PROVIDER);

		// check if enabled and if not send user to the GSP settings
		// Better solution would be to display a dialog and suggesting to 
		// go to the settings
		if (!enabled) {
		  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		  startActivity(intent);
		}
		
		police = (ImageView)findViewById(R.id.imagePolice);
		firefighter = (ImageView)findViewById(R.id.ImageFireFighter);
		ambulance = (ImageView)findViewById(R.id.ImageAmbulance);
		
		police.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 	
				Toast.makeText(getApplicationContext(), "damn", Toast.LENGTH_SHORT).show();
			}
		});
		
		firefighter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 	
				Toast.makeText(getApplicationContext(), "damn", Toast.LENGTH_SHORT).show();
			}
		});
		
		ambulance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 	
				Toast.makeText(getApplicationContext(), "damn", Toast.LENGTH_SHORT).show();
			}
		});
		
		police.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 isSpeakButtonLongPressed = true;
				 police.setImageDrawable(getResources().getDrawable(R.drawable.police_full));
				 return true;
			}
		});
		
		firefighter.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 isSpeakButtonLongPressed = true;
				 firefighter.setImageDrawable(getResources().getDrawable(R.drawable.firefighter_full));
				 return true;
			}
		});
		
		ambulance.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 isSpeakButtonLongPressed = true;
				 ambulance.setImageDrawable(getResources().getDrawable(R.drawable.ambulance_full));
				 return true;
			}
		});
		
		
		
		
		
		
		police.setOnTouchListener( new View.OnTouchListener()
		{
			 @Override
	         public boolean onTouch(View pView, MotionEvent pEvent) {
	              pView.onTouchEvent(pEvent);
	              // We're only interested in when the button is released.
	              if (pEvent.getAction() == MotionEvent.ACTION_UP) {
	                   // We're only interested in anything if our speak button is currently pressed.
	                   if (isSpeakButtonLongPressed) {
	                	    police.setImageDrawable(getResources().getDrawable(R.drawable.police));
	                        isSpeakButtonLongPressed = false;
	                   }
	              }
	              return false;
	         }
	     
		 });
		
		firefighter.setOnTouchListener( new View.OnTouchListener()
		{
			 @Override
	         public boolean onTouch(View pView, MotionEvent pEvent) {
	              pView.onTouchEvent(pEvent);
	              // We're only interested in when the button is released.
	              if (pEvent.getAction() == MotionEvent.ACTION_UP) {
	                   // We're only interested in anything if our speak button is currently pressed.
	                   if (isSpeakButtonLongPressed) {
	                	   firefighter.setImageDrawable(getResources().getDrawable(R.drawable.firefighter));
	                        isSpeakButtonLongPressed = false;
	                   }
	              }
	              return false;
	         }
	     
		 });
		
		ambulance.setOnTouchListener( new View.OnTouchListener()
		{
			 @Override
	         public boolean onTouch(View pView, MotionEvent pEvent) {
	              pView.onTouchEvent(pEvent);
	              // We're only interested in when the button is released.
	              if (pEvent.getAction() == MotionEvent.ACTION_UP) {
	                   // We're only interested in anything if our speak button is currently pressed.
	                   if (isSpeakButtonLongPressed) {
	                	   ambulance.setImageDrawable(getResources().getDrawable(R.drawable.ambulance ));
	                        isSpeakButtonLongPressed = false;
	                   }
	              }
	              return false;
	         }
	     
		 });
	}
	
	 
	 
	       

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	

}
