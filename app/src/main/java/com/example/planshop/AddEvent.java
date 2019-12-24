package com.example.planshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;

public class AddEvent extends AppCompatActivity implements PartDialog.PartDialogListener  {

    private Participants participants;
    private AdminRecipes recipes;
    private ListView listViewPart;
    private ImageButton addButton;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        listViewPart = findViewById(R.id.partListView);
        addButton = findViewById(R.id.imageButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        participants = new Participants();

    }

    public void addEvent(View view) {
        Intent event = new Intent(this, AdminEventList.class);
        startActivity(event);
    }

    public void openDialog(){
        PartDialog partDialog = new PartDialog();
        partDialog.show(getSupportFragmentManager(), "Part dialog");
    }

    @Override
    public void applyText(final String email) {
        //maybe we need to add it ti our array list of particioants and then add the list itself to the listview????????
        final String temp = email;
//        Toast.makeText(getApplicationContext(), "len" , Toast.LENGTH_LONG).show();

//        ref = FirebaseDatabase.getInstance().getReference().child("users");
//                .child("FIDlvFyuseVaTbu8EM5uDdGIxAF2");

//////////////////////////לכאן הוא מגיע
//        checkEmail(email, new OnEmailCheckListener(){
//            @Override
//            public void onSucess(boolean isRegistered) {
//                Log.d("yael2","error in the ok button");
//
//                if(isRegistered){
//                    User user = new User(email);
//                    participants.getEventUsers().add(user);
//                    Toast.makeText(getApplicationContext(), "lalalalala" , Toast.LENGTH_LONG).show();
//                } else {
//
//                    Log.d("yael1","error in the ok button");
//                }
//
//            }
//        });

        checkEmail(email);


//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.d("here!!","error in the ok button");
//
////                ref = ref.child(dataSnapshot.);
//
//                for (DataSnapshot data: dataSnapshot.getChildren()){
//                    if (data.child(temp).exists()){
//                        User user = dataSnapshot.getValue(User.class);
//
//                        participants.getEventUsers().add(user);
//
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "user dosent exist", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        Log.d("yael4","error in the ok button");


    }



    public void checkEmail(final String email){

        //check email already exist or not.
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean isNewUser = !task.getResult().getSignInMethods().isEmpty();

                        if (isNewUser) {
                            User user = new User(email);
                            participants.getEventUsers().add(user);

                            Log.d("TAG", "Is New User!");
                        } else {

                            Toast.makeText(getApplicationContext(), "User does not exict" , Toast.LENGTH_LONG).show();

                            Log.d("TAG", "Is Old User!");
                        }
                        Log.d("yael7","error in the ok button");

                        ArrayAdapter<User> adapter = new ArrayAdapter<User>(
                    AddEvent.this,
                                android.R.layout.simple_list_item_1,
                                participants.getEventUsers());

                        listViewPart.setAdapter(adapter);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "Error in fetch email is: " + e);

            }
        });

    }





}