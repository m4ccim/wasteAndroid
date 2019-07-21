/*
package com.example.andrusha.notavendingv09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends AppCompatActivity {

    private Button prevUser, nextUser, editUser, deleteUser, addUser;
    private EditText username, password, email, role, userid;
    private int index = 0;
    private List<Card> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);


        prevUser = (Button)findViewById(R.id.prevUser);
        nextUser = (Button)findViewById(R.id.nextUser);
        editUser = (Button)findViewById(R.id.editUserBtn);
        deleteUser = (Button)findViewById(R.id.deleteUser);
        addUser = (Button)findViewById(R.id.addUser);

        username = (EditText)findViewById(R.id.username_u);
        password = (EditText)findViewById(R.id.password_u);
        email = (EditText)findViewById(R.id.email_u);
        role = (EditText)findViewById(R.id.role_u);
        userid = (EditText)findViewById(R.id.userId);


        prevUser.setEnabled(true);
        nextUser.setEnabled(true);

        if(index == 0) {
            prevUser.setEnabled(false);
        }
        else if(index == users.size()) {
            nextUser.setEnabled(false);
        }

        prev();

        next();

        getUsers();

        deleteUser();
    }

    public void prev() {
        prevUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --index;
                if(index == 0) {
                    prevUser.setEnabled(false);
                }
                nextUser.setEnabled(true);

                userid.setText(String.valueOf(users.get(index).getId()));
                username.setText(users.get(index).getUserName());
                password.setText(users.get(index).getPassword());
                role.setText(String.valueOf(users.get(index).getRole()));
                email.setText(users.get(index).getEmail());
            }
        });
    }

    public void deleteUser() {
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delInd = Integer.parseInt(userid.getText().toString());
                Call<Void> call = NetworkService.getInstance()
                        .getJSONApi()
                        .deleteUser(delInd);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Code: " + response.code(),
                                    Toast.LENGTH_LONG)
                                    .show();
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "Done",
                                Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(getIntent());
                    }



                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });
    }

    public void next() {
        nextUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++index;
                if (index == users.size()) {
                    nextUser.setEnabled(false);
                }
                prevUser.setEnabled(true);

                userid.setText(String.valueOf(users.get(index).getId()));
                username.setText(users.get(index).getUserName());
                password.setText(users.get(index).getPassword());
                role.setText(String.valueOf(users.get(index).getRole()));
                email.setText(users.get(index).getEmail());
            }
        });
    }

    public void getUsers(){
        Call<List<Card>> call = NetworkService.getInstance()
                .getJSONApi()
                .getUsers();

        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code: " + response.code(),
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                users = response.body();
                if(users.isEmpty()) {
                    return;
                }

                userid.setText(String.valueOf(users.get(index).getId()));
                username.setText(users.get(index).getUserName());
                password.setText(users.get(index).getPassword());
                role.setText(String.valueOf(users.get(index).getRole()));
                email.setText(users.get(index).getEmail());
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
*/
