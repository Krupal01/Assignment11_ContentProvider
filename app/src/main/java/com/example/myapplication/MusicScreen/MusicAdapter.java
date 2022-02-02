package com.example.myapplication.MusicScreen;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    Context context;
    ArrayList<String> musicList = new ArrayList<>();

    @SuppressLint("Range")
    public MusicAdapter(Context context) {
        this.context = context;

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                musicList.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            }
            cursor.close();
        }

    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.custom_audio_item,parent,false);
        MusicViewHolder holder = new MusicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        holder.tvMusic.setText(musicList.get(position));
        holder.imgMusic.setImageResource(R.drawable.ic_baseline_music_note_24);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    protected class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView tvMusic;
        ImageView imgMusic;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMusic = itemView.findViewById(R.id.tvMusicName);
            imgMusic = itemView.findViewById(R.id.imgMusic);
        }
    }
}
