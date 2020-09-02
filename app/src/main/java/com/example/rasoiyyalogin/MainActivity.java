package com.example.rasoiyyalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rasoiyyalogin.model.ResObj;
import com.example.rasoiyyalogin.remote.ApiUtils;
import com.example.rasoiyyalogin.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = (EditText) findViewById(R.id.useredittext);
        edtPassword = (EditText) findViewById(R.id.passedittext);
        btnLogin = (Button) findViewById(R.id.loginbtn);
        userService = ApiUtils.getUserService();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }
            }
        });
    }

    private void doLogin(final String username, String password) {
        Call call =userService.login(username,password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful())
                {
                    ResObj resobj = (ResObj) response.body();
                    if(resobj.getData().getStatus().equals("Active"))
                    {
                        //login start main activity
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent) ;
                    }else {
                        Toast.makeText(MainActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}