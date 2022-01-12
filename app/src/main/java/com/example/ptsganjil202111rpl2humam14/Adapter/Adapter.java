package com.example.ptsganjil202111rpl2humam14.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsganjil202111rpl2humam14.Activity.favoriteActivity;
import com.example.ptsganjil202111rpl2humam14.R;
import com.example.ptsganjil202111rpl2humam14.model.model;
import com.example.ptsganjil202111rpl2humam14.ui.RealmHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    Callback callback;
    Realm realm;
    RealmHelper realmHelper;
    Context context;
    private List<model> dataList;
    private List<model> m;
    boolean hapus = false;

    public interface Callback{
        void calling(int v);
    }
    public Adapter(List<model> dataList, Context context, Callback callback){
        Realm.init(context);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        this.callback = callback;
        this.dataList = dataList;
        this.context = context;
        m = new ArrayList<>(dataList);
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Realm.init(context);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        model m = dataList.get(position);
        holder.judul.setText(m.getJudul());
        holder.vote.setText(m.getRating());
        Picasso.get().load(m.getGambar())
                .fit()
                .into(holder.gambar);
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hapus) {
                    deleteItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView judul, tanggal, vote;
        private ImageView favorite, gambar;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vote = itemView.findViewById(R.id.tv_listvote);
            judul = itemView.findViewById(R.id.tv_listTitle);
            gambar = itemView.findViewById(R.id.img_listImage);
            favorite = itemView.findViewById(R.id.img_favorite);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.calling(getAdapterPosition());
                }
            });
        }
    }
    private void deleteItem(int position) {
        List<model> storeList = realmHelper.delete(dataList.get(position));
        dataList = storeList;
        favoriteActivity favoriteActivity2 = new favoriteActivity();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}

