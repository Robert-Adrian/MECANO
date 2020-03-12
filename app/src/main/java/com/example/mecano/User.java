package com.example.mecano;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private boolean flag;


    private static DatabaseReference usersDatabase;

    public User(String firstName, String lastName, String userName, String email, String password) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.userName = userName.trim();
        this.email = email.trim();
        this.password = password.trim();
        userExist();
    }

    private void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void userExist() {
        usersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");



        usersDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(userName)) {
                    setFlag(true);
                    System.out.println("Flagul este " + getFlag());
                } else {
                    setFlag(false);
                    System.out.println("Flagul este " + getFlag());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }

        });


    }

    public void userAdd() {
        usersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        usersDatabase.push().child(userName);

        usersDatabase.child(userName).push().child("Nume");
        usersDatabase.child(userName).push().child("Prenume");
        usersDatabase.child(userName).push().child("Email");
        usersDatabase.child(userName).push().child("Parola");



        usersDatabase.child(userName).child("Nume").setValue(firstName);
        usersDatabase.child(userName).child("Prenume").setValue(lastName);
        usersDatabase.child(userName).child("Email").setValue(email);
        usersDatabase.child(userName).child("Parola").setValue(password);
    }



}
