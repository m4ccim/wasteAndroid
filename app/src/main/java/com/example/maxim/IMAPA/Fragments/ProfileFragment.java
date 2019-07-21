package com.example.maxim.IMAPA.Fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.maxim.IMAPA.Global;
import com.example.maxim.IMAPA.Models.Card;
import com.example.maxim.IMAPA.NetworkService;
import com.example.maxim.IMAPA.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private Button saveBtn, backBtn;
    private EditText ownerName, password, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      // getView().setContentView(R.layout.fragment_profile);

        ownerName = getView().findViewById(R.id.ownerName);
        password = getView().findViewById(R.id.password);
        email = getView().findViewById(R.id.email);

        saveBtn = getView().findViewById(R.id.save);

        fillFields();

        saveChanges();
        super.onViewCreated(view, savedInstanceState);
    }


    public void fillFields() {
            getView().findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);

            Call<Card> call = NetworkService.getInstance()
                    .getJSONApi()
                    .getCard(Global.token.getCardId());
            call.enqueue(new Callback<Card>() {
                @Override
                public void onResponse(Call<Card> call, Response<Card> response) {
                    getView().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    if (!response.isSuccessful()) {
                        Toast.makeText(getView().getContext(),
                                "Error. Code: " + response.code(),
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    ownerName.setText(response.body().getCardOwnerName());
                    email.setText(response.body().getEmail());
                    password.setText(response.body().getPasswordHash());
                }

                @Override
                public void onFailure(Call<Card> call, Throwable t) {
                    Toast.makeText(getView().getContext(),
                            t.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        }

    public void saveChanges() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = new Card();
                card.setCardId(Global.token.getCardId());
                card.setCardOwnerName(ownerName.getText().toString());
                card.setEmail(email.getText().toString());
                card.setPasswordHash(password.getText().toString());
                getView().findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                Call<Void> call = NetworkService.getInstance()
                        .getJSONApi()
                        .updateCard(Global.token.getCardId(), card);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        getView().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                        if(!response.isSuccessful()) {
                            Toast.makeText(getView().getContext(),
                                    "Error. Code: " + response.code(),
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(getView().getContext(), "Changes have been saved!",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getView().getContext(),
                                t.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });
    }
}
