package com.example.appxilio;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.os.Build;
import android.preference.PreferenceManager;

public class SettingsActivity extends Activity {

	EditText NameUser;
	EditText Email;
	EditText Phone;
	Switch SwitchAnonimo;
	ImageView SaveButtonImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		NameUser = (EditText)findViewById(R.id.editNameText);
		Email = (EditText)findViewById(R.id.editEmailText);
		Phone = (EditText)findViewById(R.id.editPhoneText);
		SwitchAnonimo = (Switch)findViewById(R.id.switchAnonimo);
		SaveButtonImage = (ImageView)findViewById(R.id.SaveImage);
		
		NameUser.setText(getIntent().getExtras().getString("Name"));
		Email.setText(getIntent().getExtras().getString("Email"));
		NameUser.setEnabled(false);
		Email.setEnabled(false);
		
		final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		boolean bool = settings.getBoolean("anonimo", true);
		SwitchAnonimo.setChecked(bool);
		
		String telefono = settings.getString("telefono", "");
		if(telefono.compareTo("")==0)
		{
			
			Phone.setEnabled(true);
			SwitchAnonimo.setEnabled(true);
			SaveButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.save));
			
		}else
		{
			Phone.setText(telefono);
			Phone.setEnabled(false);
			SwitchAnonimo.setEnabled(false);	
			
		}
		
		
		SaveButtonImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(Phone.isEnabled())
				{
					SaveButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.edit));
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("telefono",Phone.getText().toString());
					editor.putBoolean("anonimo", SwitchAnonimo.isChecked());
					editor.commit();
					Phone.setEnabled(false);
					SwitchAnonimo.setEnabled(false);	
					
				}else
				{
					SaveButtonImage.setImageDrawable(getResources().getDrawable(R.drawable.save));
					Phone.setEnabled(true);
					SwitchAnonimo.setEnabled(true);
				}
				
			}
		});
		
		
		/*
		 *SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
  SharedPreferences.Editor editor = preferences.edit();
  editor.putString("Name","Harneet");
  editor.commit();
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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

	

}
