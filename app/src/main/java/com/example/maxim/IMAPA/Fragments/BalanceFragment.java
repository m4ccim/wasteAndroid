package com.example.maxim.IMAPA.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxim.IMAPA.BillDetailsActivity;
import com.example.maxim.IMAPA.Global;
import com.example.maxim.IMAPA.Models.Bill;
import com.example.maxim.IMAPA.Models.Card;
import com.example.maxim.IMAPA.Models.CardWaste;
import com.example.maxim.IMAPA.NetworkService;
import com.example.maxim.IMAPA.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceFragment extends Fragment {

    TextView balance;
    TextView welcome;
    private List<String> names = new ArrayList<>();
    private ListView lvMain;
    private List<Bill> bills;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_balance, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        welcome = getView().findViewById(R.id.textView1);

        balance = getView().findViewById(R.id.textViewBalance);
        fillFields();
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
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Error. Code: " + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Card card = response.body();
                welcome.append(" " +card.getCardOwnerName());
                double d = card.getBalance();

                balance.setText(String.format("%.2f", d) + "₴");
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        Call<List<CardWaste>> call2 = NetworkService.getInstance()
                .getJSONApi()
                .getCardWastesByCardId(Global.token.getCardId());
        call2.enqueue(new Callback<List<CardWaste>>() {
            @Override
            public void onResponse(Call<List<CardWaste>> call, Response<List<CardWaste>> response) {
                getView().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Error. Code: " + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                List<CardWaste> cardWastes = response.body();
                for(CardWaste p : cardWastes){
                    names.add(p.getWaste().getName() + " (" + p.getAmount() + "kg) ~ " + p.getAmount()*p.getWaste().getRecyclingPrice() + "₴");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, names );

                lvMain = getView().findViewById(R.id.listViewCardWastes);
                lvMain.setAdapter(adapter);
                lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Bill product = bills.get(i);
//                        Intent intent = new Intent(getActivity(), BillDetailsActivity.class);
//                        intent.putExtra("billId", bills.get(i).getBillId());
//                        startActivity(intent);
                    }
                });

                // присваиваем адаптер списку

            }

            @Override
            public void onFailure(Call<List<CardWaste>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
