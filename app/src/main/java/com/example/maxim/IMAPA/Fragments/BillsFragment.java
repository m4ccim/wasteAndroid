package com.example.maxim.IMAPA.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maxim.IMAPA.Global;
import com.example.maxim.IMAPA.Models.Bill;
import com.example.maxim.IMAPA.BillDetailsActivity;
import com.example.maxim.IMAPA.NetworkService;
import com.example.maxim.IMAPA.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillsFragment extends Fragment {

    private List<String> names = new ArrayList<>();
    private ListView lvMain;
    private List<Bill> bills;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       return inflater.inflate(R.layout.fragment_bills, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fillFields();
        super.onViewCreated(view, savedInstanceState);
    }

    public void fillFields() {
        getView().findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        Call<List<Bill>> call = NetworkService.getInstance()
                .getJSONApi()
                .getBillsByCard(Global.token.getCardId());
        call.enqueue(new Callback<List<Bill>>() {
            @Override
            public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                getView().findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Error. Code: " + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                bills = response.body();
                for(Bill p : bills){
                    names.add("Дата : " + p.getDate().substring(0,10) + " | Сумма: " + p.getTotal() + "₴");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, names );

                lvMain = getView().findViewById(R.id.listViewInvoices);
                lvMain.setAdapter(adapter);
                lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Bill product = bills.get(i);
                        Intent intent = new Intent(getActivity(), BillDetailsActivity.class);
                       intent.putExtra("billId", bills.get(i).getBillId());
                        startActivity(intent);
                    }
                });

                // присваиваем адаптер списку

            }

            @Override
            public void onFailure(Call<List<Bill>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}