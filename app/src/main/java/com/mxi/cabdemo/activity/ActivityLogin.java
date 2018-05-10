package com.mxi.cabdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mxi.cabdemo.R;

/**
 * Created by mxicoders on 1/6/17.
 */

public class ActivityLogin extends AppCompatActivity {


    Button btnSignup,btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


    }

    private void init() {


        //Button initialization
        btnSignup = (Button)findViewById(R.id.btn_signup);
        btnSignin = (Button)findViewById(R.id.btn_signin);


       //SignUp clicklistner
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = new Intent(ActivityLogin.this,ActivitySignupMain.class);
                startActivity(signInIntent);

            }
        });

        //SignIn clicklistner
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUpIntent = new Intent(ActivityLogin.this,HomeActivity.class);
                startActivity(signUpIntent);

            }
        });
    }

}
