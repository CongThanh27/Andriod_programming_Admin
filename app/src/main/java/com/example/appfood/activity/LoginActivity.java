package com.example.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appfood.api.APIService;
import com.example.appfood.model.LoginModel;
import com.example.appfood.model.UserModel;
import com.example.appfood.retrofitClient.RetrofitClient;

import com.example.appfood.R;
import com.example.appfood.util.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;

    private ImageView Login;

    LoginModel userModel = new LoginModel();

    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });

    }

    private void AnhXa(){
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        Login = (ImageView) findViewById(R.id.btn_login);
    }

    private void UserLogin(){
        final String username = etUserName.getText().toString();
        final String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUserName.setError("Please enter your username");
            etUserName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        apiService =APIService.apiService;
        Call<LoginModel> call=apiService.login(username, password);
                call.enqueue(new Callback<LoginModel>() {


                    @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.isSuccessful() && response.body().isAdmin()==true) {
                    userModel = response.body();
                    //Log.d("logg", response.body());
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(userModel);
                    finish();
                    Intent intent = new Intent(LoginActivity.this, ProductManagerment.class);
                    startActivity(intent);
                }
                else {
                    int statusCode = response.code();
                }

                    }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    public void btnRegister_Onclick(View view) {
        UserLogin();
    }
}

