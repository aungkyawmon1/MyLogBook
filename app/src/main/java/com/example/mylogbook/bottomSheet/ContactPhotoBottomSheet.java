package com.example.mylogbook.bottomSheet;

import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogbook.Constant;
import com.example.mylogbook.GridSpacingItemDecoration;
import com.example.mylogbook.ImageClickListener;
import com.example.mylogbook.R;
import com.example.mylogbook.adapter.ImageAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ContactPhotoBottomSheet extends BottomSheetDialogFragment implements ImageClickListener {

    View v;
    RecyclerView rvContactPhoto;
    AppCompatImageView ivBack;

    ImageClickListener listener;

    ImageAdapter imageAdapter;

    public ContactPhotoBottomSheet(ImageClickListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_contact_photo,
                container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);

        defineLayout();

        setupRecyclerView();

        return v;
    }

    private void defineLayout() {
        ivBack = v.findViewById(R.id.ivBack);
        rvContactPhoto = v.findViewById(R.id.rvContactPhoto);
    }

    private void setupRecyclerView(){

        imageAdapter = new ImageAdapter(this, Constant.CONTACT_PHOTOS);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rvContactPhoto.setLayoutManager(layoutManager);
        rvContactPhoto.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(16), true));
        rvContactPhoto.setItemAnimator(new DefaultItemAnimator());
        rvContactPhoto.setAdapter(imageAdapter);
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void clickAtImage(int position) {
        listener.clickAtImage(position);
        dismiss();
    }
}
