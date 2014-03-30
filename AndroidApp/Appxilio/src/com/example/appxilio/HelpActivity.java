package com.example.appxilio;

import static com.example.appxilio.Constant.FIRST_COLUMN;
import static com.example.appxilio.Constant.SECOND_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class HelpActivity extends Activity {

	private ArrayList<HashMap<String,String>> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		ListView lview = (ListView) findViewById(R.id.listView1);
        populateList();
        listviewAdapter adapter = new listviewAdapter(this, list);
        lview.setAdapter(adapter);
	}
	
	private void populateList() {
		 
        list = new ArrayList<HashMap<String,String>>();
 
        HashMap<String, String> temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN,"Colored Notebooks");
            temp.put(SECOND_COLUMN, "By NavNeet");
        list.add(temp);
 
        HashMap<String, String> temp1 = new HashMap<String, String>();
            temp1.put(FIRST_COLUMN,"Diaries");
            temp1.put(SECOND_COLUMN, "By Amee Products");
        list.add(temp1);
 
        HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put(FIRST_COLUMN,"Note Books and Stationery");
            temp2.put(SECOND_COLUMN, "By National Products");
        list.add(temp2);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
