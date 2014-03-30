package com.example.appxilio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.os.AsyncTask;
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
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

	
	boolean isSpeakButtonLongPressed = false;
	ImageView police;
	ImageView firefighter;
	ImageView ambulance;
	ImageView ImageSettings;
	ImageView ImageHistory;
	ImageView ImageHelp;
	ImageView ImageStatus;
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
		ImageHelp = (ImageView)findViewById(R.id.imageView1);
		ImageStatus  = (ImageView)findViewById(R.id.ImageStatus);
		ImageStatus.setVisibility(View.INVISIBLE);
		
		police.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ImageStatus.setVisibility(View.VISIBLE);
				new HttpAsyncTask().execute("http://casterly-rock-nodejs-98907.use1-2.nitrousbox.com/denuncia/addPanico");
			 	//POST("http://casterly-rock-nodejs-98907.use1-2.nitrousbox.com/denuncia/addPanico", denuncia);
				//Toast.makeText(getApplicationContext(), "damn", Toast.LENGTH_SHORT).show();
			}
		});
		
		firefighter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 	
				Toast.makeText(getApplicationContext(), "Denuncia Enviada!", Toast.LENGTH_SHORT).show();
			}
		});
		
		ambulance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 	
				Toast.makeText(getApplicationContext(), "Denuncia Enviada!", Toast.LENGTH_SHORT).show();
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
		
ImageHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent help = new Intent(getApplicationContext(), HelpActivity.class);

				startActivity(help);
				
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
	                        Toast.makeText(getApplicationContext(), "Denuncia Enviada!", Toast.LENGTH_SHORT).show();
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
	                        Toast.makeText(getApplicationContext(), "Denuncia Enviada!", Toast.LENGTH_SHORT).show();
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
	                        Toast.makeText(getApplicationContext(), "Denuncia Enviada!", Toast.LENGTH_SHORT).show();
	                   }
	              }
	              return false;
	         }
	     
		 });
	}	
	private LocationManager locationManager;
	  private String provider;
		public posicionGPS position(){
			   double latitude ,longitude ;
			
			GPSTracker gps= new GPSTracker(MainActivity.this);
	    if(gps.canGetLocation()){
	
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
//             Toast.makeText(getApplicationContext(), "Tu Posicion es - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();   
            
            posicionGPS pos = new posicionGPS();
                       
            pos.latitud = String.valueOf(15.561674);
            pos.longitud = String.valueOf(-88.021313);
            
            return pos;
          
            // \n is for new line
            
        }else{
        	//longitude=latitude=0.0;
        	// can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
		
		return new posicionGPS();
	}
		
		private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	        @Override
	        protected String doInBackground(String... urls) {
	 

				final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				boolean bool = settings.getBoolean("anonimo", true);				
				String telefono = settings.getString("telefono", "");
				
			 	posicionGPS gps = position();
			 	infoUsuario user = new infoUsuario();
			 	user.correoElectronico = email;
			 	user.nombreCompleto = FullName;
			 	user.numeroTelefono = telefono;
			 	denuncia denuncia = new denuncia();
			 	denuncia.boton = "policia";
			 	denuncia.esAnonima = bool;
			 	denuncia.latitud = gps.latitud;
			 	denuncia.longitud = gps.longitud;
			 	denuncia.infoUsuario = user;
	 
	            return POST(urls[0],denuncia);
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	            Toast.makeText(getBaseContext(), "Denuncia Enviada!", Toast.LENGTH_LONG).show();
	       }
	    }
	 
	public String POST(String url, denuncia denuncia){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
 
            String json = "";
 
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("latitud", denuncia.latitud);
            jsonObject.accumulate("longitud", denuncia.longitud);  
            JSONObject user = new JSONObject();
            user.accumulate("correoElectronico", denuncia.infoUsuario.correoElectronico);
            user.accumulate("nombreCompleto", denuncia.infoUsuario.nombreCompleto);
            user.accumulate("numeroTelefono", denuncia.infoUsuario.numeroTelefono);
            jsonObject.accumulate("infoUsuario", user);
            jsonObject.accumulate("boton", denuncia.boton);
            jsonObject.accumulate("esAnonima", denuncia.esAnonima);
 
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
 
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person); 
 
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
 
            // 6. set httpPost Entity
            httpPost.setEntity(se);
 
            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
 
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
 
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        // 11. return result
        return result;
    }
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }  
 
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;    
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
