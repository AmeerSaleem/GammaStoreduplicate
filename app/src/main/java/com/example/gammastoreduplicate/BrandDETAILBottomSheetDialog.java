package com.example.gammastoreduplicate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BrandDETAILBottomSheetDialog extends BottomSheetDialogFragment {

    TextView top_text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_brand_detail_bottom_sheet, container, false);
        top_text = v.findViewById(R.id.top_texter);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;

        if(BRANDDetailWorkingActivity.bottomsheet_type_selected == 1){
            top_text.setText(getString(R.string.sort_by));
        }
        else{
            top_text.setText(getString(R.string.refine_by));
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }
}