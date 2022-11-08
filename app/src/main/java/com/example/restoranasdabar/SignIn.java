package com.example.restoranasdabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignIn extends AppCompatActivity {

    EditText i_password,i_email;
    Button signInButton, signUp_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        i_password= findViewById(R.id.edtPsw);
        i_email= findViewById(R.id.edtEMailIn);
        signInButton = findViewById(R.id.btnSignIn);
        signUp_btn= findViewById(R.id.btnSignUp);
        signInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String password, email;
                password = i_password.getText().toString();
                email = i_email.getText().toString();

                if(!password.equals("")&&!email.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "Email";
                            field[1] = "Password";

                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;

                            PutData putData = new PutData("https://9d4b-193-219-164-7.eu.ngrok.io/databaseIn/login.php", "POST", field, data);


                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    if(result.equals("Success")){
                                        Toast.makeText(getApplicationContext(),"Succesfull Connection",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"You need to fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }
}