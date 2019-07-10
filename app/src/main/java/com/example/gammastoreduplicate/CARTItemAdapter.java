package com.example.gammastoreduplicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CARTItemAdapter extends  RecyclerView.Adapter<CARTItemAdapter.CartViewHolder>{

    Context context;
    ArrayList<ProductClass> cart_items;

    CARTItemAdapter.onItemClickListener mListener;

    public interface onItemClickListener{

        public void onViewClick(int position);
        public void onViewClick2(int position);

    }

    public void setOnClickListener(CARTItemAdapter.onItemClickListener listener){mListener = listener;}

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_cart_item_list,viewGroup,false);
        CartViewHolder cvh = new CartViewHolder(v,mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {

        ProductClass cart_pc =  cart_items.get(i);

        cartViewHolder.cart_item_name.setText(cart_pc.product_name);
        cartViewHolder.cart_item_cost_in_$.setText(String.valueOf(cart_pc.cost_in_$));
        cartViewHolder.cart_items_quantity.setText(String.valueOf(cart_pc.quantity));

        Glide
                .with(context)
                .load(cart_pc.product_bitmaps.get(0))
                .into(cartViewHolder.cart_item_image);
    }

    @Override
    public int getItemCount() {
        return cart_items.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

    TextView cart_item_name;
    TextView cart_item_cost_in_$;
    ImageView cart_item_image;
    TextView cart_items_quantity;
    TextView cart_item_quantity_plus;
    TextView cart_item_quantity_minus;

    public CartViewHolder(View itemView, final CARTItemAdapter.onItemClickListener listener){

        super(itemView);

        cart_item_name = itemView.findViewById(R.id.cart_item_name);
        cart_item_cost_in_$ = itemView.findViewById(R.id.cart_item_cost);
        cart_item_image = itemView.findViewById(R.id.cart_item_image);
        cart_item_quantity_plus = itemView.findViewById(R.id.cart_plus);
        cart_item_quantity_minus = itemView.findViewById(R.id.cart_minus);
        cart_items_quantity = itemView.findViewById(R.id.cart_quantity);

        cart_item_quantity_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onViewClick(position);
                    }
                }
            }
        });

        cart_item_quantity_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onViewClick2(position);
                    }
                }
            }
        });
    }




    }

    public CARTItemAdapter(Context context, ArrayList<ProductClass> cart_items){

        this.context = context;
        this.cart_items = cart_items;

    }

    public void deleteItem(int position){
        ProductClass recentlyDeleted = cart_items.get(position);
        int recentlyDeletedPosition = position;
        cart_items.remove(position);
        notifyItemRemoved(position);
    }
}
