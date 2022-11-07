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

public class SignUp extends AppCompatActivity {

    EditText i_name,i_surname,i_password,i_email,i_phone_num;
    Button signupButton, login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        i_name= findViewById(R.id.edtName);
        i_surname= findViewById(R.id.edtSurname);
        i_password= findViewById(R.id.edtPsw1);
        i_email= findViewById(R.id.edtEMail);
        i_phone_num= findViewById(R.id.edtPhone);
        signupButton = findViewById(R.id.btnSignUp);
        login_btn = findViewById(R.id.btnSignIn);

        signupButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String name, surname, password, email, phone;
                name = i_name.getText().toString();
                surname = i_surname.getText().toString();
                password = i_password.getText().toString();
                email = i_email.getText().toString();
                phone = i_phone_num.getText().toString();
                if(!name.equals("")&&!surname.equals("")&&!password.equals("")&&!email.equals("")&&!phone.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "Name";
                            field[1] = "Surname";
                            field[2] = "Email";
                            field[3] = "Password";
                            field[4] = "Phone_num";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = name;
                            data[1] = surname;
                            data[2] = email;
                            data[3] = password;
                            data[4] = phone;

                            PutData putData = new PutData("http://192.168.56.1/databaseIn/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                                    if(result.equals("Correct")){
                                        Toast.makeText(getApplicationContext(),"Succesfull Registracion",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),SignIn.class);
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
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
                finish();
            }
        });

    }

}