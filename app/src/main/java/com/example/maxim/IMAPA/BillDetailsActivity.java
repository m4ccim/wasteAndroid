package com.example.maxim.IMAPA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxim.IMAPA.Models.BillDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillDetailsActivity extends AppCompatActivity {


    BillDetails billDetails;
    List<String> names = new ArrayList<>();
    private ListView lvMain;
    TextView invoiceId;
    TextView discount;
    TextView sum;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_products);
        invoiceId = findViewById(R.id.invoiceId);
        discount = findViewById(R.id.discount);
        sum = findViewById(R.id.sum);
        date = findViewById(R.id.date);

        fillView();
    }
    private void fillView() {
        Call<BillDetails> call = NetworkService.getInstance()
                .getJSONApi()
                .getBillDetails(getIntent().getIntExtra("billId", 0));
        call.enqueue(new Callback<BillDetails>() {
            @Override
            public void onResponse(Call<BillDetails> call, Response<BillDetails> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Error. Code: " + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                billDetails = response.body();
                double fullsum = 0;
                for (BillDetails.ProductDetails p : billDetails.getProductDetails()) {
                    names.add(p.getProduct().getName() + " | к-сть: " + p.getAmount() + " | цiна: " + p.getProduct().getBasePrice() + "₴");
                    fullsum+=p.getAmount()*p.getProduct().getBasePrice();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BillDetailsActivity.this,
                        android.R.layout.simple_list_item_1, names);

                lvMain = findViewById(R.id.listViewProducts);
                lvMain.setAdapter(adapter);
                sum.setText(String.format("%.2f", billDetails.getBill().getTotal())+ "₴");

                discount.setText(String.format("%.2f", fullsum-billDetails.getBill().getTotal())+ "₴");

                invoiceId.setText(String.valueOf(billDetails.getBill().getBillId()));


                date.setText(billDetails.getBill().getDate().substring(0,10));

            }

            @Override
            public void onFailure(Call<BillDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
