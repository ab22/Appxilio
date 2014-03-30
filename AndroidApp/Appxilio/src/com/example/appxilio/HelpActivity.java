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
            temp.put(FIRST_COLUMN,"\n911\n(Emergencias)\n");
            temp.put(SECOND_COLUMN, "\nLlamar en caso de querer contactarse con la policia. En caso de incendios y querer contactarse con los bomberos. Para reportar y recibir asistencia con respecto accidentes de transito y en caso de sufrir maltrato domestico.\n");
        list.add(temp);
 
        HashMap<String, String> temp1 = new HashMap<String, String>();
            temp1.put(FIRST_COLUMN,"\n2237-1800\n2220-6248\n(Cruz Roja)\n");
            temp1.put(SECOND_COLUMN, "\nAl ocurrir una emergencia medica de caracter urgente.\n");
        list.add(temp1);
 
        HashMap<String, String> temp2 = new HashMap<String, String>();
            temp2.put(FIRST_COLUMN,"\n2234-4400\n2234-8409\n(COPECO)\n");
            temp2.put(SECOND_COLUMN, "\nOrientada a la prevención y reducción del riesgo, la atención de las emergencias, la recuperación y adaptación al cambio climático para garantizar la vida, los bienes materiales y ambientales de la nación.\n");
        list.add(temp2);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
