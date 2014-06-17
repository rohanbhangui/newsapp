package com.rohanapps.newsapp;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class AddPostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_post);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_post, menu);
		return true;
	}
	
	public void postData(View view) {
		String postTitle=((EditText) findViewById(R.id.post_title)).getText().toString();
		String postContent=((EditText) findViewById(R.id.post_content)).getText().toString();
		String postTimestamp=System.currentTimeMillis() + "";
		
		if ((postTitle != null && !postTitle.isEmpty()) && (postContent != null && !postContent.isEmpty()))
		{
			
			Firebase f = new Firebase("https://rohannewsapp.firebaseio.com/posts");
			
			
			Firebase newPushRef = f.push();
			
			newPushRef.setValue(new Post(postContent, postTimestamp, postTitle));
	
			Intent intent = new Intent(this, MainActivity.class);
			
			startActivity(intent);
		
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
