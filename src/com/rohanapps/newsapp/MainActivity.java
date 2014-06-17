package com.rohanapps.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout.Alignment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//final TextView textView = (TextView) findViewById(R.id.output);

		Firebase f = new Firebase("https://rohannewsapp.firebaseio.com/posts");

		f.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
				SpannableStringBuilder postContent = new SpannableStringBuilder();
				TextView view = new TextView(MainActivity.this);
				view.setBackgroundColor(Color.WHITE);
				view.setPadding(0, 20, 0, 20);
				
				LinearLayout ll = (LinearLayout) findViewById(R.id.outputLayout);
				
				Post post = snapshot.getValue(Post.class);
				SpannableString titleSpan = new SpannableString(post.title);
				SpannableString contentSpan = new SpannableString(post.content);
				SpannableString timestampSpan = new SpannableString(post.timestamp);

				titleSpan.setSpan(new TextAppearanceSpan(MainActivity.this, android.R.style.TextAppearance_Large), 0, post.title.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				contentSpan.setSpan(new TextAppearanceSpan(MainActivity.this, android.R.style.TextAppearance_Medium), 0, post.content.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				contentSpan.setSpan(new ForegroundColorSpan(R.color.lt_grey1), 0, post.content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				timestampSpan.setSpan(new TextAppearanceSpan(MainActivity.this, android.R.style.TextAppearance_Small), 0, post.timestamp.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				timestampSpan.setSpan(new AlignmentSpan.Standard(Alignment.ALIGN_OPPOSITE), 0, post.timestamp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				timestampSpan.setSpan(new ForegroundColorSpan(R.color.lt_grey1), 0, post.timestamp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

				postContent.insert(0, titleSpan);
				postContent.insert(titleSpan.length(), System.getProperty("line.separator"));
				postContent.insert((titleSpan.length() + 1), contentSpan);
				postContent.insert((titleSpan.length() + 1 + contentSpan.length()), System.getProperty("line.separator"));
				postContent.insert((titleSpan.length() + 1 + contentSpan.length() + 1), timestampSpan);
				postContent.insert((titleSpan.length() + 1 + contentSpan.length() + 1 + timestampSpan.length()), System.getProperty("line.separator"));

				view.setText(postContent);
				
				ll.addView(view, 0);

			}

			@Override
			public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
			}

			@Override
			public void onChildRemoved(DataSnapshot snapshot) {
			}

			@Override
			public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
			}

			@Override
			public void onCancelled(FirebaseError error) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_settings) {
			Intent postIntent = new Intent(this, AddPostActivity.class);
			startActivity(postIntent);
		}

		return super.onOptionsItemSelected(item);
	}

}
