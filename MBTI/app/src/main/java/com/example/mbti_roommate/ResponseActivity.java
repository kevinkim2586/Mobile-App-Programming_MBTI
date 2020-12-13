package com.example.mbti_roommate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResponseActivity extends AppCompatActivity implements ResponseListViewAdapter.ListBtnClickListener{
    ListView listView;
    ResponseListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        Bundle bundleObject = getIntent().getExtras();
        ArrayList<UserInfo> uInfos = (ArrayList<UserInfo>)bundleObject.getSerializable("UserInfos");
        listView = findViewById(R.id.responselistView);
        adapter = new ResponseListViewAdapter(this,R.layout.profile_response,uInfos,this);
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
