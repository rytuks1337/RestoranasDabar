package com.example.restoranasdabar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    EditText i_name,i_surname,i_password,i_email,i_phone_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        i_name= findViewById(R.id.edtName);
        i_surname= findViewById(R.id.edtSurname);
        i_password= findViewById(R.id.edtPsw1);
        i_email= findViewById(R.id.edtEMail);
        i_phone_num= findViewById(R.id.edtPhone);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[5];
                field[0] = "Name";
                field[1] = "Surname";
                field[2] = "Email";
                field[3] = "Password";
                field[4] = "Phone_num";
                //Creating array for data
                String[] data = new String[5];
                data[0] = i_name.getText().toString();
                data[1] = i_surname.getText().toString();
                data[2] = i_password.getText().toString();
                data[3] = i_email.getText().toString();
                data[4] = i_phone_num.getText().toString();

                PutData putData = new PutData("https://projects.vishnusivadas.com/AdvancedHttpURLConnection/putDataTest.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                    }
                }
                //End Write and Read data with URL
            }
        });
    }

}