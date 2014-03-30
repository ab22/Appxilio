package com.example.appxilio;

import java.util.LinkedList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.provider.ContactsContract;
import android.provider.Settings;

public class MainActivity extends Activity {

	
	boolean isSpeakButtonLongPressed = false;
	ImageView police;
	ImageView firefighter;
	ImageView ambulance;
	ImageView ImageSettings;
	ImageView ImageHistory;
	String FullName;
	String email;
	String Phone;

	
	
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
		FullName = getFullName();
		email = getUsername();
		
		police = (ImageView)findViewById(R.id.imagePolice);
		firefighter = (ImageView)findViewById(R.id.ImageFireFighter);
		ambulance = (ImageView)findViewById(R.id.ImageAmbulance);
		ImageSettings = (ImageView)findViewById(R.id.ImageSettings);
		ImageHistory = (ImageView)findViewById(R.id.ImageHistory);
		
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
		
		ImageSettings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("Name",FullName);  
                bundle.putString("Email",email);
               settings.putExtras(bundle);   
				startActivity(settings);
				
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
		
		
		ImageHistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent history = new Intent(getApplicationContext(), ComplaintHistory.class);
				startActivity(history);
				
				
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
	
	
	public String getUsername(){
	    AccountManager manager = AccountManager.get(this); 
	    Account[] accounts = manager.getAccountsByType("com.google"); 
	    List<String> possibleEmails = new LinkedList<String>();

	    for (Account account : accounts) {
	      // TODO: Check possibleEmail against an email regex or treat
	      // account.name as an email address only for certain account.type values.
	      possibleEmails.add(account.name);
	    }

	    if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
	        String email = possibleEmails.get(0);
	        return email;
	    }
	    return null;
	}
	
	private String getFullName()
	{
		Cursor c = this.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
		int count = c.getCount();
		String[] columnNames = c.getColumnNames();
		boolean b = c.moveToFirst();
		int position = c.getPosition();
		if (count == 1 && position == 0) {
		    for (int j = 0; j < columnNames.length; j++)
		    {
		    	
		        String columnName = columnNames[j];
		        String columnValue = c.getString(c.getColumnIndex(columnName));
		        if(columnName.compareTo("display_name")==0)
		        {
		        	c.close();
		        	return columnValue;
		        }
		        
		    }
		}
		c.close();
		return "";
		
	}
	
	
	private String getMyPhoneNumber(){
		TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 
		String number = tm.getLine1Number();
        return number;
    }

    private String getMy10DigitPhoneNumber(){
        String s = getMyPhoneNumber();
        return s.substring(2);
    }
	
	

	

}
