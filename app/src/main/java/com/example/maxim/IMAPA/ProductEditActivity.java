package com.example.maxim.IMAPA;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ProductEditActivity extends AppCompatActivity {
    private String id;
    private EditText name;
    private EditText type;
    private EditText unit;
    private EditText costPerUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        unit = findViewById(R.id.unit);
        costPerUnit = findViewById(R.id.costPerUnit);

        id = getIntent().getStringExtra("id");
        name.setText(getIntent().getStringExtra("name"));
        type.setText(getIntent().getStringExtra("type"));
        unit.setText(getIntent().getStringExtra("unit"));
        costPerUnit.setText(Double.toString(getIntent().getDoubleExtra("costPerUnit", 0)));
    }
}
