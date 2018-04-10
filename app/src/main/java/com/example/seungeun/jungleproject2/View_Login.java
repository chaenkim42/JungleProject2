package com.example.seungeun.jungleproject2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//로그인 화면

public class View_Login extends AppCompatActivity {
    private Button loginBtn, lostPwBtn;
    private BackPressCloseHandler backPressCloseHandler;
    private Context context;
    private EditText Id,Pw;
    private Handler mHandler;
    private int resultCode;
    private ProgressDialog progressDialog;
    private String resultString = "";
    private TextView signUpBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_login);
        context = this;
        backPressCloseHandler = new BackPressCloseHandler(this);
        progressDialog = new ProgressDialog(View_Login.this);
        mHandler = new Handler();

        //BtnOnClickListener class 아래에 구현함.
        BtnOnClickListener onClickListener = new BtnOnClickListener();

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(onClickListener);
        signUpBtn = (TextView)findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(onClickListener);
        Id = (EditText)findViewById(R.id.inputID);
        Pw = (EditText)findViewById(R.id.inputPW);
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    class BtnOnClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View view){

            switch (view.getId()) {
                case R.id.loginBtn:
                    if (Id.getText().toString().isEmpty() || Pw.getText().toString().isEmpty()) {
                        Toast.makeText(context, "아이디와 패스워드를 모두 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else{
                        runOnUiThread(new Runnable() {
                            DataRead login = new DataRead();

                            @Override
                            public void run() {
                                progressDialog = ProgressDialog.show(View_Login.this, "",
                                        "잠시만 기다려 주세요.", true);

                                String user_email = Id.getText().toString();
                                String user_password = Pw.getText().toString();

                                login.execute("user_login.php", "user_email", user_email, "user_password", user_password);

                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            if (progressDialog != null && progressDialog.isShowing()) {
                                                resultCode = login.getResultCode();
                                                resultString = login.getPostResponseResult();

                                                if (resultCode == 200) {
                                                    Boolean check = resultString.contains("로그인 성공");
                                                    if (check == true) {
                                                        startActivity(new Intent(View_Login.this, Menu_MainHome.class));
                                                        //CookieManager

                                                    } else {
                                                        Toast.makeText(context, "없는 회원정보 입니다.", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(context, "로그인 오류", Toast.LENGTH_SHORT).show();
                                                }
                                                progressDialog.dismiss();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 1000);
                            }
                        });
                    }
                    break;
                case R.id.signUpBtn :
                    startActivity(new Intent(View_Login.this,View_SignUp.class));
                    finish();
                    break;

            }
        }
    }
}


