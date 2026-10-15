package com.example.warehouseinventory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> productList;

    public ProductAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Setting the data to the UI
        holder.nameTxt.setText(product.getName());
        holder.stockTxt.setText(String.valueOf(product.getStock()));
        holder.skuTxt.setText("SKU: " + product.getSkuId()); // Displaying the automated SKU

        holder.deleteBtn.setOnClickListener(v -> {
            // Get the current position safely
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                productList.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, productList.size());
            }
        });
    }

    @Override
    public int getItemCount() { return productList.size(); }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, stockTxt, skuTxt;
        Button deleteBtn;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.productName);
            stockTxt = itemView.findViewById(R.id.productStock);

            // This line is where the error is likely happening
            skuTxt = itemView.findViewById(R.id.productSku);

            deleteBtn = itemView.findViewById(R.id.btnDelete);
        }
    }
}