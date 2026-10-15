package com.example.warehouseinventory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private EditText editProductName, editInitialStock;
    private Button btnAddProduct;
    private RecyclerView recyclerView;

    private ArrayList<Product> productList;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        editProductName = findViewById(R.id.editProductName);
        editInitialStock = findViewById(R.id.editInitialStock);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editProductName.getText().toString().trim();
                String stockStr = editInitialStock.getText().toString().trim();

                if (name.isEmpty() || stockStr.isEmpty()) {
                    Toast.makeText(DashboardActivity.this, "Please enter name and stock", Toast.LENGTH_SHORT).show();
                    return;
                }

                int stock = Integer.parseInt(stockStr);

                // --- SKU / DUPLICATE CHECK LOGIC ---
                boolean alreadyExists = false;
                for (Product p : productList) {
                    if (p.getName().equalsIgnoreCase(name)) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (alreadyExists) {
                    Toast.makeText(DashboardActivity.this,
                            "Error: '" + name + "' already exists in inventory!",
                            Toast.LENGTH_LONG).show();
                } else {
                    // 1. Generate the Automated SKU ID
                    String generatedSku = generateSKU(name);

                    // 2. Add new product with the SKU ID
                    productList.add(new Product(generatedSku, name, stock));
                    adapter.notifyItemInserted(productList.size() - 1);

                    // 3. Clear inputs and notify user
                    editProductName.setText("");
                    editInitialStock.setText("");
                    Toast.makeText(DashboardActivity.this, "Stock added with SKU: " + generatedSku, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Generates a unique SKU ID: WH-[First 3 Letters]-[Unique Suffix]
     */
    private String generateSKU(String name) {
        String prefix = "WH"; // Warehouse identifier

        // Take first 3 letters of the product name, or the whole name if it's shorter
        String code = name.length() >= 3 ? name.substring(0, 3).toUpperCase() : name.toUpperCase();

        // Generate a simple unique number based on the current time (last 4 digits)
        String uniqueID = String.valueOf(System.currentTimeMillis() % 10000);

        return prefix + "-" + code + "-" + uniqueID;
    }
}