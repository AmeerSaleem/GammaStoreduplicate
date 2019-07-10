package com.example.gammastoreduplicate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CartFragment extends Fragment {

    static ConstraintLayout cart_populated_layout;
    static LinearLayout cart_empty_layout;
    TextView cart_pay_button;

    TextView total_quantity;
    static TextView total_cost;

//    static ArrayList<ProductClass> cart_items;
    RecyclerView rcv_cart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        cart_pay_button = v.findViewById(R.id.cart_PAY_button);
        cart_pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DeliveryActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getActivity(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                intent.putExtra("entry", "main");
                startActivity(intent, bundle);
//                getActivity().finish();
            }
        });
        cart_populated_layout = v.findViewById(R.id.cart_item_view);
        cart_empty_layout = v.findViewById(R.id.cart_items_empty_view);
        total_quantity = v.findViewById(R.id.cart_quantity);
        total_cost = v.findViewById(R.id.cart_items_total_cost);
        rcv_cart = v.findViewById(R.id.recycler_cart);
//        cart_items = new ArrayList<>();
//        MainActivity.cart_items.add(MainShopFragment.recommended_items.get(0));
//        MainActivity.cart_items.add(MainShopFragment.recommended_items.get(1));

        final CARTItemAdapter adapter = new CARTItemAdapter(getContext(), MainActivity.cart_items);
        adapter.setOnClickListener(new CARTItemAdapter.onItemClickListener() {
            @Override
            public void onViewClick(int position) {
                MainActivity.cart_items.get(position).setQuantity(MainActivity.cart_items.get(position).getQuantity() + 1);
//                int temp = pc.quantity + 1;
//                pc.setQuantity(temp);
                setTotalCost();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onViewClick2(int position) {
                if (MainActivity.cart_items.get(position).getQuantity() != 1) {
                    MainActivity.cart_items.get(position).setQuantity(MainActivity.cart_items.get(position).getQuantity() - 1);
                    setTotalCost();
                    adapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcv_cart.setLayoutManager(manager);
        rcv_cart.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(rcv_cart);
        setTotalCost();
        return v;

    }

    public static void setTotalCost() {

        if (MainActivity.cart_items.size() == 0) {
            cart_populated_layout.setVisibility(View.GONE);
            cart_empty_layout.setVisibility(View.VISIBLE);
        } else {

            cart_populated_layout.setVisibility(View.VISIBLE);
            cart_empty_layout.setVisibility(View.GONE);
            int total_price = 0;

            for (int i = 0; i < MainActivity.cart_items.size(); i++) {

                total_price += (MainActivity.cart_items.get(i).cost_in_$ * MainActivity.cart_items.get(i).quantity);

            }

            total_cost.setText(String.valueOf(total_price));

        }
    }
}