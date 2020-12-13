package com.example.mbti_roommate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity implements RequestListViewAdapter.ListBtnClickListener{
    ListView listView;
    RequestListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Intent intent = getIntent();
        Bundle bundleObject = getIntent().getExtras();
        ArrayList<UserInfo> uInfos = (ArrayList<UserInfo>)bundleObject.getSerializable("UserInfos");
        listView = findViewById(R.id.requestlistView);
        adapter = new RequestListViewAdapter(this,R.layout.profile_request,uInfos,this);
        Log.e("Object",String.valueOf(adapter==null));
        listView.setAdapter(adapter);

        for(int i=0;i<uInfos.size();++i){
            adapter.addItem(uInfos.get(i));
        }
    }

    @Override
    public void onListBtnClick(int position) {
        UserInfo userInfo = (UserInfo)adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), IndividualProfileResult.class);
        intent.putExtra("UserInfo",userInfo);
        startActivity(intent);
    }
}
