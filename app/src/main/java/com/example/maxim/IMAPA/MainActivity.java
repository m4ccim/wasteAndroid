package com.example.maxim.IMAPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxim.IMAPA.Models.Card;
import com.example.maxim.IMAPA.Models.TokenPost;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView wrongInput, ukraine, english;
    private Button loginBtn;
    private EditText username, password;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login();
    }

    public List<String> read(String path) {
        List<String> result = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(path);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(reader);
            int a = 0;
            //StringBuffer strBuffer = new StringBuffer();
            String lines = null;
            while ((lines = buffer.readLine()) != null) {
                //strBuffer.append(lines + "\n");
                result.add(lines);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public void write(String content, String path) {
        try {
            FileOutputStream fos = openFileOutput(path, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {



        wrongInput = (TextView)findViewById(R.id.wrongInput);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        username = (EditText)findViewById(R.id.loginText);
        password = (EditText)findViewById(R.id.passwordText);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".RegistrationActivity");
                startActivity(intent);
            }
        });



        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                        Card card = new Card();
                        card.setEmail(username.getText().toString());
                        card.setPasswordHash(password.getText().toString());
                        Call<TokenPost> call = NetworkService.getInstance()
                                .getJSONApi()
                                .getToken(card);

                        call.enqueue(new Callback<TokenPost>() {
                            @Override
                            public void onResponse(Call<TokenPost> call, Response<TokenPost> response) {
                                findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                                if (!response.isSuccessful()) {
                                    wrongInput.setVisibility(View.VISIBLE);
                                    return;
                                }

                                wrongInput.setVisibility(View.INVISIBLE);
                                Global.token = response.body();
                                if(!response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Sorry, go to site and leave a request for registration",
                                            Toast.LENGTH_LONG).show();
                                    return;
                                }
                                Intent intent = new Intent(".SecondActivity");
                                finishAffinity();
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<TokenPost> call, Throwable t) {
                                wrongInput.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                }
        );
    }
}
