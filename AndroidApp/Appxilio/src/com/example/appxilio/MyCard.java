package com.example.appxilio;

import android.view.View;
import android.widget.TextView;

import com.fima.cardsui.objects.RecyclableCard;

public class MyCard extends RecyclableCard {

	public MyCard(String title){
		super(title);
	}

	@Override
	protected int getCardLayoutId() {
		return R.layout.card_play;
	}

	@Override
	protected void applyTo(View convertView) {
		((TextView) convertView.findViewById(R.id.title)).setText(title);
	}
}
