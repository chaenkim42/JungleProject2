package com.example.seungeun.jungleproject2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;


public class View_SignUp extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private BackPressCloseHandler backPressCloseHandler;
    private Button backBtn, signupFinishBtn;
    private Context context;
    private EditText inputID, inputPW, inputName, inputStudentID, inputConfirmPW;
    private Handler handler;
    private int idCheckState = NOT_OK;
    private int pwCheckState = NOT_OK;
    private int pwLengthCheckState = NOT_OK;
    private int s_idLengthCheckState = NOT_OK;
    private int resultCode = 1;
    private TextView checkId, checkPw;
    private ProgressDialog progressDialog;
    private String user_email, user_classnumber, user_major, user_password, user_name, user_signupdate;
    private String resultString = "";

    //스피너 변수
    private ArrayAdapter majorAdapter;
    private Spinner inputMajor;

    private static final int NOT_OK = 0;
    private static final int AVAIABLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_signup);
        backPressCloseHandler = new BackPressCloseHandler(this);
        builder = new AlertDialog.Builder(View_SignUp.this);
        context = this;
        handler = new Handler();
        progressDialog = new ProgressDialog(View_SignUp.this);

        BtnOnClickListener onClickListener = new BtnOnClickListener();
        EditorAcionListener onEditorAcionListener = new EditorAcionListener(); //엔터키 눌렀을 때 다음 칸으로 넘어가는 것

        backBtn = (Button) findViewById(R.id.toLoginView_backBtn);
        backBtn.setOnClickListener(onClickListener);
        signupFinishBtn = (Button) findViewById(R.id.finishBtn);
        inputID = (EditText) findViewById(R.id.signUp_inputID);
        inputPW = (EditText) findViewById(R.id.signUp_inputPW);
        inputMajor = (Spinner) findViewById(R.id.select_major);
        inputName = (EditText) findViewById(R.id.input_name);
        inputStudentID = (EditText) findViewById(R.id.input_studentID);
        inputConfirmPW = (EditText) findViewById(R.id.signUp_confirmPW);
        checkId = (TextView) findViewById(R.id.checkID);
        checkPw = (TextView) findViewById(R.id.checkPW);

        setUserInfo();
        runOnUiThread(new Runnable() {
            @Override
                public void run() {
                    //아이디 중복체크
                inputID.addTextChangedListener( new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        final String instantEmail = s.toString();
                        if(instantEmail.length()>0) {
                            final DataRead idcheck = new DataRead();
                            idcheck.execute("id_checking.php", "user_email", instantEmail);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        resultCode = idcheck.getResultCode();
                                        resultString = idcheck.getPostResponseResult();

                                        checkId.setVisibility(View.VISIBLE);
                                        if (resultCode == 200) {
                                            if (resultString.contains("중복된 아이디 입니다.")) {
                                                checkId.requestFocus();
                                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                                                idCheckState = NOT_OK;
                                                checkId.setTextColor(RED);
                                                checkId.setText("이미 있는 아이디 입니다.");
                                            } else if (resultString.contains("사용 가능한 아이디 입니다.")) {
                                                checkId.setTextColor(GREEN);
                                                checkId.setText(instantEmail+" 는 사용 가능한 아이디 입니다.");
                                                idCheckState = AVAIABLE;
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, 300);
                        }else{
                            checkId.setTextColor(RED);
                            checkId.setText("아이디를 입력하세요.");
                            idCheckState = NOT_OK;
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });
            }
        });

        //비밀번호 입력란 textwatcher
        inputPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inputConfirmPW.getText().toString().length() > 0) {
                    final String instantInputPw = s.toString();
                    final String instantConfirmPw = inputConfirmPW.getText().toString();
                    if (instantConfirmPw.length() > 0 && instantInputPw.length() >= 5) {
                        pwLengthCheckState = AVAIABLE;
                        if (instantConfirmPw.equals(instantInputPw)) {
                            checkPw.setTextColor(GREEN);
                            checkPw.setText("비밀번호 일치");
                            pwCheckState = AVAIABLE;
                        } else {
                            checkPw.setTextColor(RED);
                            checkPw.setText("비밀번호 불일치");
                            pwCheckState = NOT_OK;
                        }
                    } else if (instantConfirmPw.length() > 0 && instantInputPw.length() < 5) {
                        checkPw.setTextColor(RED);
                        checkPw.setText("비밀번호를 5자 이상 입력하세요.");
                        pwCheckState = NOT_OK;
                        pwLengthCheckState = NOT_OK;
                    } else {
                        checkPw.setTextColor(RED);
                        checkPw.setText("비밀번호를 확인해 주세요.");
                        pwCheckState = NOT_OK;
                        pwLengthCheckState = NOT_OK;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //비밀번호 확인 입력란 textwatcher
        inputConfirmPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String instantConfirmPw = s.toString();
                final String instantInputPw = inputPW.getText().toString();
                if(instantConfirmPw.length()>0 && instantInputPw.length() >= 5) {
                    pwLengthCheckState = AVAIABLE;
                    if (instantConfirmPw.equals(instantInputPw)){
                        checkPw.setTextColor(GREEN);
                        checkPw.setText("비밀번호 일치");
                        pwCheckState = AVAIABLE;
                    }else{
                        checkPw.setTextColor(RED);
                        checkPw.setText("비밀번호 불일치");
                        pwCheckState = NOT_OK;
                    }
                }else if(instantConfirmPw.length()>0 && instantInputPw.length()<5){
                    checkPw.setTextColor(RED);
                    checkPw.setText("비밀번호를 5자 이상 입력하세요.");
                    pwCheckState = NOT_OK;
                    pwLengthCheckState = NOT_OK;
                }else{
                    checkPw.setTextColor(RED);
                    checkPw.setText("비밀번호를 확인해 주세요.");
                    pwCheckState = NOT_OK;
                    pwLengthCheckState = NOT_OK;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // 학번 입력란 textwatcher : 학번 9자리 잘 입력했는지 확인하기 위해
        inputStudentID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final String instantStudentId = s.toString();
                if(instantStudentId.length() == 9) {
                    s_idLengthCheckState = AVAIABLE;

                }else{
                    s_idLengthCheckState = NOT_OK;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //버튼리스너 _ 제출버튼
        signupFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserInfo();
                //user_major=getSpinnerItem(v,position);
                DateFormat dfDate = new SimpleDateFormat("yyyy/mm/dd");
                user_signupdate = dfDate.format(Calendar.getInstance().getTime());
                final AlertDialog dialog = builder.create();
                if(idCheckState == 1 && pwCheckState == 1 && s_idLengthCheckState ==1) { // AVAILABLE = 1
                    runOnUiThread(new Runnable() {
                        DataInsert signUp = new DataInsert();

                        @Override
                        public void run() {
                            //... UI 업데이트 작업
                            signUp.execute("user_signup.php",
                                    "user_email", user_email, "user_major", user_major, "user_classnumber", user_classnumber,
                                    "user_password", user_password, "user_name", user_name, "user_signupdate", user_signupdate);

                            progressDialog = ProgressDialog.show(View_SignUp.this, "",
                                    "잠시만 기다려 주세요.", true);

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        if (progressDialog != null && progressDialog.isShowing()) {
                                            progressDialog.dismiss();

                                            resultCode = signUp.getResultCode();
                                            resultString = signUp.getPostResponseResult();
                                            Log.i("test main resultcode", "" + resultCode);
                                            Log.i("test main string", resultString);

                                            //final AlertDialog dialog = builder.create(); // 위의 builder를 생성할 AlertDialog 객체 생성

                                            if (resultCode == 200) {
                                                //회원가입 성공
                                                if (resultString.contains("SQL문 처리 성공")) {
                                                    Log.d("회원가입 성공구문", "SQL문 처리 성공");
                                                    builder.setTitle("회원가입 성공!")
                                                            .setMessage("성공적으로 회원가입이 완료되었습니다. " +
                                                                    "\n서비스 이용을 위해 로그인 해주세요")
                                                            .setNeutralButton("로그인 하러 가기", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dlg, int sumthin) {
                                                                    startActivity(new Intent(View_SignUp.this, View_Login.class));
                                                                    finish();
                                                                    dialog.dismiss();
                                                                }
                                                            }).show();
                                                }
                                                //빈칸 있는 경우
                                                else if (resultString.contains("데이터를 입력하세요") == true) {
                                                    builder.setMessage("빈칸을 모두 채워주세요.")
                                                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dlg, int sumthin) {
                                                                    dialog.dismiss();
                                                                }
                                                            }).show();
                                                }
                                            }
                                            //그 외 모든 오류의 경우
                                            else {
                                                builder.setTitle("오류메세지")
                                                        .setMessage("오류가 발생했습니다. \n다시 시도해 주세요.")
                                                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dlg, int sumthin) {
                                                                dialog.dismiss();
                                                            }
                                                        }).show();
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, 1000);
                        }
                    });
                }else if(idCheckState == 0 ){ // NOT_OK = 0
                    Toast.makeText(context,"아이디가 중복되었습니다.",Toast.LENGTH_SHORT).show();
                }else if(pwLengthCheckState == 0){
                    Toast.makeText(context,"비밀번호를 5자 이상 입력해주세요.",Toast.LENGTH_SHORT).show();
                }else if(pwCheckState == 0 ){ // NOT_OK = 0
                    Toast.makeText(context,"비밀번호가 일치하지 않습니다.\n다시 확인해주세요.",Toast.LENGTH_SHORT).show();
                }else if(s_idLengthCheckState == 0){
                    Toast.makeText(context,"학번 9자리를 정확히 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //학과 선택 spinner를 major_select.xml 의 배열과 매칭하는 부분

        String[] str = getResources().getStringArray(R.array.select_major_array);
        Log.d("signup view test ", str[0]+" "+str[1]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, str);
        Spinner spi = (Spinner)findViewById(R.id.select_major);
        spi.setAdapter(adapter);
        spi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        user_major=getSpinnerItem(view,position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                }
        );

    }
    //spinner에서 선택한 값을 textview로 프린트하는 함수
    public String getSpinnerItem(View v, int position){
        Spinner sp = (Spinner)findViewById(R.id.select_major);
        //TextView tx = (TextView)findViewById(R.id.selected_major);
        String res = "";
        if(sp.getSelectedItemPosition()>0){
            res = (String)sp.getAdapter().getItem(sp.getSelectedItemPosition());
        }
        return res;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //String에 사용자가 입력한 값 저장하는 함수
    public void setUserInfo() {
        user_email = inputID.getText().toString();
        //user_major = inputMajor.getText().toString();
        user_classnumber = inputStudentID.getText().toString();
        user_password = inputPW.getText().toString();
        user_name = inputName.getText().toString();
    }

    class BtnOnClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {


            switch (view.getId()) {
                case R.id.toLoginView_backBtn: //회원가입 창에서 뒤로가기 버튼을 눌렀을 때 다시 로그인 화면으로
                    startActivity(new Intent(View_SignUp.this, View_Login.class));
                    finish();
                    break;

                case R.id.finishBtn: // 회원가입 버튼 눌렀을때

                    break;

            }

        }
    }


    class EditorAcionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_NEXT:
                    Log.i("testEnterAction", "enter");
                    break;
                default:
                    // 기본 엔터키 동작
                    return false;
            }
            return true;
        }
    }

}
