package com.example.pprasad.testchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by PPRASAD on 9/6/2017.
 */

public class LoginPage extends AppCompatActivity {

    private EditText _eEmailID;
    private Button _btnNext;
    private String _sEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        _eEmailID = (EditText)findViewById(R.id.text_id);
        _btnNext = (Button)findViewById(R.id.btn_next);

        _btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sEmail = _eEmailID.getText().toString().trim();
                if(_sEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Your Email ID", Toast.LENGTH_SHORT).show();
                }
                if (isEmailValid(_sEmail))
                {
                    Intent i = new Intent(LoginPage.this, ChatActivity.class);
                    i.putExtra("email",_sEmail);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Email ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public static boolean isEmailValid(String sEmail) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = sEmail;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}
