package com.amirht97.examplemvp.peresenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirht97.examplemvp.R;
import com.amirht97.examplemvp.model.MovieResponse;
import com.amirht97.examplemvp.model.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Search> data;

    public MovieAdapter(Context context, List<Search> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Picasso.get().load(data.get(position).getPoster()).into(holder.img_poster);
        Picasso.get().load(data.get(position).getPoster()).into(holder.img_BigPoster);

        holder.txt_title.setText(data.get(position).getTitle());
        holder.txt_type.setText(data.get(position).getType());
        holder.txt_plot.setText(data.get(position).getImdbID());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title,txt_type,txt_plot;
        CircleImageView img_poster;
        ImageView img_BigPoster;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_type = itemView.findViewById(R.id.txt_type);
            txt_plot = itemView.findViewById(R.id.txt_plot);
            img_poster = itemView.findViewById(R.id.img_poster);
            img_BigPoster = itemView.findViewById(R.id.img_BigPoster);
        }
    }
}
