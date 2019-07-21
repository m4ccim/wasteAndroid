//package com.example.maxim.IMAPA;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class StockActivity extends AppCompatActivity {
//
//    Stock stock;
//    List<String> names = new ArrayList<>();
//    private ListView lvMain;
//    TextView stockId;
//    TextView name;
//    TextView description;
//    TextView address;
//    List<StockItem> stockItems;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stock);
//        stockId = findViewById(R.id.stockId);
//        name = findViewById(R.id.name);
//        description = findViewById(R.id.description);
//        address = findViewById(R.id.address);
//
//        fillFields();
//        fillView();
//    }
//
//    private void fillView() {
//        Call<List<StockItem>> call = NetworkService.getInstance()
//                .getJSONApi()
//                .getStockItems(Global.token.stockId);
//        call.enqueue(new Callback<List<StockItem>>() {
//            @Override
//            public void onResponse(Call<List<StockItem>> call, Response<List<StockItem>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Error. Code: " + response.code(),
//                            Toast.LENGTH_LONG).show();
//                    return;
//                }
//                stockItems = response.body();
//                for (StockItem p : stockItems) {
//                    names.add(p.getProduct().getName() + " : " + p.getAmount() + " " + p.getProduct().getUnit());
//                }
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(StockActivity.this,
//                        android.R.layout.simple_list_item_1, names);
//
//                lvMain = findViewById(R.id.listViewProducts);
//                lvMain.setAdapter(adapter);
//                lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        StockItem product = stockItems.get(i);
//                        Intent intent = new Intent(StockActivity.this, ProductEditActivity.class);
//                        intent.putExtra("id", product.getId());
//                        intent.putExtra("name", product.getProduct().getName());
//                        intent.putExtra("amount", product.getAmount());
//                        intent.putExtra("unit", product.getProduct().getUnit());
//                        intent.putExtra("costPerUnit", product.getProduct().getCostPerUnit());
//                        intent.putExtra("type", product.getProduct().getType());
//                        startActivity(intent);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<List<StockItem>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),
//                        t.getMessage(),
//                        Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
//    }
//
//    public void fillFields() {
//        Call<Stock> call = NetworkService.getInstance()
//                .getJSONApi()
//                .getStock(Global.token.stockId);
//        call.enqueue(new Callback<Stock>() {
//            @Override
//            public void onResponse(Call<Stock> call, Response<Stock> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Error. Code: " + response.code(),
//                            Toast.LENGTH_LONG).show();
//                    return;
//                }
//                stock = response.body();
//                description.setText(stock.getDescription());
//                address.setText(stock.getAddress());
//                name.setText(stock.getName());
//                stockId.setText(String.valueOf(stock.getId()));
//
//            }
//
//            @Override
//            public void onFailure(Call<Stock> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),
//                        t.getMessage(),
//                        Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
//    }
//}