package com.example.ptsganjil202111rpl2humam14.ui;

import android.util.Log;

import com.example.ptsganjil202111rpl2humam14.model.model;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;
    List<model> storeList;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final model models) {
        realm.executeTransaction( new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(model.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    models.setId(nextId);
                    model itemModel = realm.copyToRealm(models);
                    final RealmResults<model> item = realm.where(model.class).findAll();
                } else {
                    Log.e("Log", "execute: Database not Exist");
                }
            }
        });
    }


    public List<model> getAllMovies() {
        RealmResults<model> results = realm.where(model.class).findAll();
        return results;
    }

    public List delete(model movie){
        final RealmResults<model> model = realm.where(model.class).equalTo("gambar", movie.getGambar()).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteAllFromRealm();
                final RealmResults<model> allItems = realm.where(model.class).findAll();
                storeList = realm.copyFromRealm(allItems);;
                Collections.sort(storeList);
            }
        });
        Log.d("Store List", ""+storeList.size());
        return storeList;
    }

}
