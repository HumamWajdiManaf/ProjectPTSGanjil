package com.example.ptsganjil202111rpl2humam14.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsganjil202111rpl2humam14.R;
import com.example.ptsganjil202111rpl2humam14.model.model;
import com.example.ptsganjil202111rpl2humam14.ui.RealmHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.security.auth.callback.Callback;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Mainadapter extends RecyclerView.Adapter<Mainadapter.ViewHolder> {
    private List<model> Model;
    Callback callback;
    Context context;
    Realm realm;
    RealmHelper realmHelper;
    public interface  Callback{
        void call(int v);
    }


    public Mainadapter (List<model> Model, Context context, Callback callback){
        this.callback = callback;
        this.context = context;
        this.Model = Model;
    }
    @NonNull
    @Override
    public Mainadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mainadapter.ViewHolder holder, int position) {
        Realm.init(context);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
       model i  = Model.get(position);
       holder.judul.setText(i.getJudul());
       holder.vote.setText(i.getRating());
        Picasso.get()
                .load(Model.get(position).getGambar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.picture);
        if (Model.get(position).getFavorite()) {
            holder.favorite.setImageResource(R.drawable.favorite_red);
        } else {
            holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Model.get(position).getFavorite()) {
                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    Toast.makeText(context, "Film dihapus dari list favorite kamu", Toast.LENGTH_SHORT).show();
                    Model.get(position).setFavorite(false);
                    realmHelper.delete(Model.get(position));
                } else {
                    Model.get(position).setFavorite(true);
                    holder.favorite.setImageResource(R.drawable.favorite_red);
                    Toast.makeText(context, "Film ditambahkan ke list favorite kamu", Toast.LENGTH_SHORT).show();
                    realmHelper.save(Model.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView judul, vote;
        private ImageView picture , favorite;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tv_listTitle);
            vote = itemView.findViewById(R.id.tv_listvote);
            picture = itemView.findViewById(R.id.img_listImage);
            favorite = itemView.findViewById(R.id.img_favorite);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.call(getAdapterPosition());
                }
            });
        }
    }
}
