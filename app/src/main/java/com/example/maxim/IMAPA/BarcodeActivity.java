package com.example.maxim.IMAPA;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxim.IMAPA.Models.Bill;
import com.example.maxim.IMAPA.Models.Card;
import com.example.maxim.IMAPA.Models.CardWaste;
import com.example.maxim.IMAPA.Models.Product;
import com.example.maxim.IMAPA.Models.ProductWaste;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarcodeActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1;
    private ImageView imgview;
    private TextView txtView;
    private List<String> names = new ArrayList<>();
    private ListView lvMain;
    private List<Bill> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgview = (ImageView) findViewById(R.id.imgview);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
;

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable drawable = (BitmapDrawable) imgview.getDrawable();
                Bitmap myBitmap = drawable.getBitmap();
                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX  | Barcode.EAN_8)
                        .build();
                txtView = (TextView) findViewById(R.id.txtContent);
                if (!barcodeDetector.isOperational()) {
                    txtView.setText("Could not set up the detector!");
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Barcode> barcodes = barcodeDetector.detect(frame);

                Barcode thisCode = barcodes.valueAt(0);
                txtView.setText(thisCode.rawValue);
                fillFields();
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgview.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(BarcodeActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(BarcodeActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public void fillFields() {
        Call<Product> call = NetworkService.getInstance()
                .getJSONApi()
                .getProduct(Integer.parseInt(txtView.getText().toString().substring(0,7)));
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Error. Code: " + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Product product = response.body();
                TextView name = findViewById(R.id.txtProductName);
                TextView baseprice = findViewById(R.id.txtBasePrice);
                name.setText(product.getName());
                baseprice.setText(Double.toString(product.getBasePrice()) + "₴");

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

        Call<List<ProductWaste>> call2 = NetworkService.getInstance()
                .getJSONApi()
                .getProductWastes(Integer.parseInt(txtView.getText().toString().substring(0,7)));
        call2.enqueue(new Callback<List<ProductWaste>>() {
            @Override
            public void onResponse(Call<List<ProductWaste>> call, Response<List<ProductWaste>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Error. Code: " + response.code(),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                names.clear();
                List<ProductWaste> cardWastes = response.body();
                for(ProductWaste p : cardWastes){
                    names.add(p.getWaste().getName() + " (" + p.getAmount() + "kg) ~ " + p.getAmount()*p.getWaste().getRecyclingPrice() + "₴");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, names );

                lvMain = findViewById(R.id.listViewProductWastes);
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
            public void onFailure(Call<List<ProductWaste>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }




}
