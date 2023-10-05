package com.example.mylogbook.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogbook.ImageClickListener;
import com.example.mylogbook.R;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private static final String TAG = "ImageAdapter";

    private int[] contactPhotos;

    private ImageClickListener listener;

    public ImageAdapter(ImageClickListener listener, int[] dataSet) {
        this.listener = listener;
        this.contactPhotos = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivContactPhoto;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

            ivContactPhoto = (ImageView) v.findViewById(R.id.ivContactPhoto);
        }

        public ImageView getImageView() {
            return ivContactPhoto;
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_photo_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getImageView().setImageResource(contactPhotos[position]);

        viewHolder.itemView.setOnClickListener(view -> {
            listener.clickAtImage(position);
        });


    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return contactPhotos.length;
    }
}
