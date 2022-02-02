package com.example.myapplication.ContactScreen;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    Context context;
    ArrayList<ContactItem> contactList = new ArrayList<>();

    @SuppressLint("Range")
    public ContactAdapter(Context context) {
        this.context = context;

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        String name;
        String phone = null;
        String imgSrc = "";
        String id;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                imgSrc = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));

                Cursor pcursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = ?", new String[]{id}, null);
                if (pcursor.getCount()>0){
                    while (pcursor.moveToNext()){
                        phone = pcursor.getString(pcursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                }
                pcursor.close();
                contactList.add(new ContactItem(name,phone,imgSrc));
            }
            cursor.close();
        }


    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_contact_item,parent,false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.tvName.setText(contactList.get(position).Name);
        holder.tvPhone.setText(contactList.get(position).Phone);

        if (contactList.get(position).ImgSrc!=null){
            Uri myuri = Uri.parse(contactList.get(position).ImgSrc);
            holder.imgPerson.setImageURI(myuri);
        }else {
            holder.imgPerson.setImageResource(R.drawable.ic_baseline_person_24);
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvPhone;
        ImageView imgPerson;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPersonName);
            tvPhone = itemView.findViewById(R.id.tvPersonNumber);
            imgPerson = itemView.findViewById(R.id.imgPerson);
        }
    }
}
