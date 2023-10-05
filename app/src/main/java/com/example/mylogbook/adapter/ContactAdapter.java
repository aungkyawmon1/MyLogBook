package com.example.mylogbook.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylogbook.ContactModel;
import com.example.mylogbook.ImageClickListener;
import com.example.mylogbook.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>  {


    private static final String TAG = "ContactAdapter";

    private ArrayList<ContactModel> contacts;


    public ContactAdapter( ArrayList<ContactModel> dataSet) {
        this.contacts = dataSet;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivContactPhoto;
        private final TextView tvName, tvDateOfBirth, tvEmail;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

            ivContactPhoto = (ImageView) v.findViewById(R.id.ivContactPhoto);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvDateOfBirth = (TextView) v.findViewById(R.id.tvDateOfBirth);
            tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        }

        public ImageView getImageView() {
            return ivContactPhoto;
        }

        public TextView getNameView() { return  tvName; }

        public TextView getDateOfBirthView() { return tvDateOfBirth; }

        public TextView getEmailView() { return  tvEmail; }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_list_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getImageView().setImageResource(contacts.get(position).getPhoto());

        viewHolder.getNameView().setText(contacts.get(position).getName());

        viewHolder.getDateOfBirthView().setText(contacts.get(position).getDateOfBirth());

        viewHolder.getEmailView().setText(contacts.get(position).getEmail());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
