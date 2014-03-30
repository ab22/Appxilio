package com.example.appxilio;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

	Button btnShowLocation;
	TextView lat;
	TextView lon;
	private LocationManager locationManager;
	  private String provider;
    // GPSTracker class
    GPSTracker gps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	
}
