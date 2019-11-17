package com.prakash.nestedhorizontalrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.prakash.nestedhorizontalrecyclerview.Adapter.MyItemGroupAdapter;
import com.prakash.nestedhorizontalrecyclerview.Interface.IFirebaseLoadListener;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements IFirebaseLoadListener {

    AlertDialog dialog;
    IFirebaseLoadListener iFirebaseLoadListener;

    RecyclerView my_recycler_view;
    DatabaseReference myData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myData= FirebaseDatabase.getInstance().getReference("MyData");
        dialog= new SpotsDialog.Builder().setContext(this).build();

        iFirebaseLoadListener = this;

        // view
        my_recycler_view= (RecyclerView)findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this));

// load data
        getFirebaseData();
    }

    private void getFirebaseData() {
        dialog.show();
        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<itemGroup> itemGroups= new ArrayList<>();
                for(DataSnapshot groupSnapshot: dataSnapshot.getChildren())
                {
                    itemGroup itemGroup= new itemGroup();
                    itemGroup.setHeaderTitle(groupSnapshot.child("headerTitle").getValue(true).toString());
                    GenericTypeIndicator<ArrayList<itemData>> genericTypeIndicator= new GenericTypeIndicator<ArrayList<itemData>>(){} ;
                    itemGroup.setListItem(groupSnapshot.child("listItem").getValue(genericTypeIndicator));
                    itemGroups.add(itemGroup);


                }
                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                iFirebaseLoadListener.onFirebaseLoadFailed(databaseError.getMessage());

            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<itemGroup> itemGroupList) {

        MyItemGroupAdapter adapter = new MyItemGroupAdapter(this,itemGroupList);
        my_recycler_view.setAdapter(adapter);

        dialog.dismiss();
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        dialog.dismiss();

    }
}
