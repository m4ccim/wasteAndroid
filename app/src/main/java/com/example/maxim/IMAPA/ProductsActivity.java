//package com.example.maxim.IMAPA;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ProductsActivity extends AppCompatActivity {
//
//    private List<String> names = new ArrayList<>();
//    private  ListView lvMain;
//    private List<product> products;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_products);
//        fillFields();
//
//
//        // создаем адаптер
//
//    }
//
//    public void fillFields() {
//        Call<List<product>> call = NetworkService.getInstance()
//                .getJSONApi()
//                .getProducts();
//        call.enqueue(new Callback<List<product>>() {
//            @Override
//            public void onResponse(Call<List<product>> call, Response<List<product>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Error. Code: " + response.code(),
//                            Toast.LENGTH_LONG).show();
//                    return;
//                }
//                products = response.body();
//                for(product p : products){
//                    names.add(p.getName() + " : "+ p.getCostPerUnit()+" ₴/"+p.getUnit());
//                }
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProductsActivity.this,
//                        android.R.layout.simple_list_item_1, names );
//
//                lvMain = findViewById(R.id.listViewProducts);
//                lvMain.setAdapter(adapter);
//                lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        product product = products.get(i);
//                        Intent intent = new Intent(ProductsActivity.this, ProductEditActivity.class);
//                        intent.putExtra("id", product.getId());
//                        intent.putExtra("name", product.getName());
//                        intent.putExtra("costPerUnit", product.getCostPerUnit());
//                        intent.putExtra("unit", product.getUnit());
//                        intent.putExtra("type", product.getType());
//                        startActivity(intent);
//                    }
//                });
//
//                // присваиваем адаптер списку
//
//            }
//
//            @Override
//            public void onFailure(Call<List<product>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),
//                        t.getMessage(),
//                        Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
//    }
//}
