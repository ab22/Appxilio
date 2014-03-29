package com.example.appxilio;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
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

public class MainActivity extends Activity {

	public static int LONG_PRESS_TIME = 500; // Time in miliseconds
	boolean isSpeakButtonLongPressed = false;

	  final Handler _handler = new Handler(); 
	  Runnable _longPressed = new Runnable() { 
	      public void run() {
	          Toast.makeText(getApplicationContext(), "Long press", Toast.LENGTH_SHORT).show();
	      }   
	  };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView police = (ImageView)findViewById(R.id.imagePolice);
		
		police.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 	
				Toast.makeText(getApplicationContext(), "damn", Toast.LENGTH_SHORT).show();
			}
		});
		
		police.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				 isSpeakButtonLongPressed = true;
				 Toast.makeText(getApplicationContext(), "press begin", Toast.LENGTH_SHORT).show();
	             return true;
			}
		});
		
		police.setOnTouchListener(speakTouchListener);
	}
	
	 private View.OnTouchListener speakTouchListener = new View.OnTouchListener()
	 {
		 @Override
         public boolean onTouch(View pView, MotionEvent pEvent) {
              pView.onTouchEvent(pEvent);
              // We're only interested in when the button is released.
              if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                   // We're only interested in anything if our speak button is currently pressed.
                   if (isSpeakButtonLongPressed) {
                	   Toast.makeText(getApplicationContext(), "press end", Toast.LENGTH_SHORT).show();
                        isSpeakButtonLongPressed = false;
                   }
              }
              return false;
         }
     
	 };
	 
	 

        

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
