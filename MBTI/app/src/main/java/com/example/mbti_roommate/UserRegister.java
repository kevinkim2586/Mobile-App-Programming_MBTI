package com.example.mbti_roommate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


//회원가입 클래스

public class UserRegister extends AppCompatActivity {

    private EditText id_EditText;
    private EditText pw_EditText;
    private EditText pwCheck_EditText;
    private EditText name_EditText;
    private Spinner mbti_spinner;
    private Spinner age_spinner;
    private EditText email_EditText;
    private Spinner email_spinner;
    private Spinner dorm_spinner;
    private Spinner univ_spinner;
    private EditText comment_EditText;
    private EditText contact_EditText;
    private Spinner PMAM_spinner;
    private Spinner psTime_spinner;
    private Spinner psHour_spinner;

    private Button signup_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mbti_spinner = findViewById(R.id.MBTI_spinner);
        age_spinner = findViewById(R.id.age_spinner);
        email_spinner = findViewById(R.id.email_spinner);
        dorm_spinner = findViewById(R.id.dorm_spinner);
        univ_spinner = findViewById(R.id.univ_spinner);
        PMAM_spinner = findViewById(R.id.PMAM_spinner);
        psTime_spinner = findViewById(R.id.psTime_spinner);
        psHour_spinner = findViewById(R.id.psHour_spinner);
        signup_button = findViewById(R.id.signup_button);

        ArrayAdapter mbtiAdapter = ArrayAdapter.createFromResource
                (this, R.array.mbti_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter ageAdapter = ArrayAdapter.createFromResource
                (this, R.array.age_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter emailAdapter = ArrayAdapter.createFromResource
                (this, R.array.email_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter dormAdapter = ArrayAdapter.createFromResource
                (this, R.array.dorm_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter univAdapter = ArrayAdapter.createFromResource
                (this, R.array.univ_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter PMAMAdapter = ArrayAdapter.createFromResource
                (this, R.array.PMAM_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter psTimeAdapter = ArrayAdapter.createFromResource
                (this, R.array.pstime_array, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter psHourAdapter = ArrayAdapter.createFromResource
                (this, R.array.pshour_array, android.R.layout.simple_spinner_dropdown_item);

        mbtiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbti_spinner.setAdapter(mbtiAdapter);

        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age_spinner.setAdapter(ageAdapter);

        emailAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        email_spinner.setAdapter(emailAdapter);

        dormAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dorm_spinner.setAdapter(dormAdapter);

        univAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        univ_spinner.setAdapter(univAdapter);

        PMAMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PMAM_spinner.setAdapter(PMAMAdapter);

        psTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        psTime_spinner.setAdapter(psTimeAdapter);

        psHourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        psHour_spinner.setAdapter(psHourAdapter);
        id_EditText = (EditText) findViewById(R.id.userID_editText);
        pw_EditText = (EditText) findViewById(R.id.userPW_editText);
        pwCheck_EditText = (EditText) findViewById(R.id.userPWCheck_editText);
        name_EditText = (EditText) findViewById(R.id.userName_editText);
        final RadioGroup gender_RadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        final RadioGroup isSmoker_RadioGroup = (RadioGroup) findViewById(R.id.isSmokerRadioGroup);
        email_EditText = (EditText)findViewById(R.id.email_editText);
        comment_EditText = (EditText) findViewById(R.id.pcomment);
        //contact_EditText = (EditText)findViewById(R.id.user_contact);


        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.e("here come in!","click begin");
                String id = id_EditText.getText().toString();
                String password = pw_EditText.getText().toString();
                String pname = name_EditText.getText().toString();
                //성별
                int pgenderInt = 2;         //2는 에러값
                RadioButton gender_RadioButton = (RadioButton) findViewById(gender_RadioGroup.getCheckedRadioButtonId());
                if ((gender_RadioButton.getText().toString()).equals("남자"))
                    pgenderInt = 0;
                else if ((gender_RadioButton.getText().toString().equals("여자")))
                    pgenderInt = 1;
                String pgender = Integer.toString(pgenderInt);
                //mbti
                //순서는 아래와 같다(@values/array.xml이랑 똑같이 하긴 했는데 나중에 수정 필요하면 할께요)
                //INTJ, INTP, ENTJ, ENTP, INFJ, INFP, ENFJ, ENFP, ISTJ, ISTP, ESTJ, ESTP, ISFJ, ISFP, ESFJ, ESFP
                int pmbtiInt = 16;          // 16은 에러값
                if ((mbti_spinner.getSelectedItem().toString()).equals("INTJ"))
                    pmbtiInt = 0;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("INTP"))
                    pmbtiInt = 1;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ENTJ"))
                    pmbtiInt = 2;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ENTP"))
                    pmbtiInt = 3;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("INFJ"))
                    pmbtiInt = 4;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("INFP"))
                    pmbtiInt = 5;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ENFJ"))
                    pmbtiInt = 6;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ENFP"))
                    pmbtiInt = 7;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ISTJ"))
                    pmbtiInt = 8;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ISTP"))
                    pmbtiInt = 9;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ESTJ"))
                    pmbtiInt = 10;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ESTP"))
                    pmbtiInt = 11;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ISFJ"))
                    pmbtiInt = 12;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ISFP"))
                    pmbtiInt = 13;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ESFJ"))
                    pmbtiInt = 14;
                else if ((mbti_spinner.getSelectedItem().toString()).equals("ESFP"))
                    pmbtiInt = 15;
                String pmbti = Integer.toString(pmbtiInt);
                //기숙사
                int pdormitoryInt = 11110002;   //11110002는 에러값
                if ((dorm_spinner.getSelectedItem().toString()).equals("첨성관"))
                    pdormitoryInt = 11110001;
                //else if((dorm_spinner.getSelectedItem().toString()).equals("누리관"))
                String pdormitory = Integer.toString(pdormitoryInt);
                //대학교
                int univInt = 1113;             //1113은 에러값
                if ((univ_spinner.getSelectedItem().toString()).equals("경북대학교(대구캠)"))
                    univInt = 1111;
                else if ((univ_spinner.getSelectedItem().toString()).equals("경북대학교(상주캠)"))
                    univInt = 1112;
                String univ = Integer.toString(univInt);

                //흡연 유무
                int psmokeBool = 2;         //2는 에러값
                RadioButton psmoke_RadioButton = (RadioButton) findViewById(isSmoker_RadioGroup.getCheckedRadioButtonId());
                if ((psmoke_RadioButton.getText().toString()).equals("예"))
                    psmokeBool = 1;
                else if ((gender_RadioButton.getText().toString().equals("아니오")))
                    psmokeBool = 0;
                String psmoke = String.valueOf(psmokeBool);
                //자기에 대한 상세 설명
                String pcomment = comment_EditText.getText().toString();
                //연령
                String page = age_spinner.getSelectedItem().toString();
                //이메일
                String email = email_EditText.getText().toString() + "@" + email_spinner.getSelectedItem().toString();
                String pcontact = email;

                //수면 시간, 수면 시각
                int pmam = 0;
                if ((PMAM_spinner.getSelectedItem().toString()).equals("PM"))
                    pmam = 12;
                String pstime = Integer.toString((pmam + Integer.parseInt(psTime_spinner.getSelectedItem().toString())));
                String pshour = psHour_spinner.getSelectedItem().toString();

                String pmajor = "11110001";

                sendRequest(id, password, pname, pgender, pmbti, pdormitory, univ, pmajor, email, psmoke, pcomment, page, pcontact, pstime, pshour);

            }
        });
    }

    //회원가입하기 버튼 클릭
    public void signUpButtonClicked(View v) {

    }

    public void openMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void sendRequest(final String id, final String password, final String pname, final String pgender, final String pmbti, final String pdormitory, final String univ,final String pmajor, final String email,
                            final String psmoke, final String pcomment, final String page, final String pcontact, final String pstime, final String pshour){
        RequestQueue requestQueue = Volley.newRequestQueue(UserRegister.this);  //이 에러가 도대체 뭘까요??????????????????
        String url = urlManager.registerURL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                     try {
                         JSONObject jsonObj = new JSONObject(response);
                         boolean isSuccessed = jsonObj.getBoolean("success");
                         Log.e("Success",String.valueOf(isSuccessed));
                         if(isSuccessed) {
                             openMainPage();
                         }
                         else{
                             Toast info = Toast.makeText(getApplicationContext(),jsonObj.getString("reason"),Toast.LENGTH_LONG);
                             info.show();
                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String,String> params = new HashMap<String,String>();
                params.put("id",id);
                params.put("password",password);
                params.put("pname", pname);
                params.put("pgender", pgender);
                params.put("pmbti", pmbti);
                params.put("pdormitory", pdormitory);
                params.put("univ", univ);
                params.put("pmajor",pmajor);
                params.put("email", email);
                params.put("psmoke", psmoke);
                params.put("pcomment", pcomment);
                params.put("page", page);
                params.put("pcontact", pcontact);
                params.put("pstime", pstime);
                params.put("pshour", pshour);
                return params;
            }
            @Override
            public  Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("Context-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
