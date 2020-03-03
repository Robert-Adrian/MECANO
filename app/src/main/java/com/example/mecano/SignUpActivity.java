package com.example.mecano;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

//firebase libraries
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.UserProfileChangeRequest;



public class SignUpActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    EditText firstName;
    EditText lastName;
    EditText userName;
    EditText email;
    EditText userPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mAuth = FirebaseAuth.getInstance();

    }

    public void register(View view) {

        firstName = (EditText) findViewById(R.id.editText6);
        lastName = (EditText) findViewById(R.id.editText7);
        userName = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText5);
        userPwd = (EditText) findViewById(R.id.editText4);

        if (firstName.getText().toString().equals("") || lastName.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT);
        } else if (userName.getText().toString().equals("")){
            Toast.makeText(SignUpActivity.this, "Enter a valid username", Toast.LENGTH_SHORT);
        } else if (email.getText().toString().equals("")) {
            Toast.makeText(SignUpActivity.this, "Enter a valid email", Toast.LENGTH_SHORT);
        } else if (userPwd.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a valid password", Toast.LENGTH_SHORT);
        } else {

            mAuth.createUserWithEmailAndPassword(email.getText().toString(), userPwd.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){

                @Override
                public void onComplete(Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Sign in success!", Toast.LENGTH_SHORT);
                        user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(getApplicationContext(), "The account already exist !", Toast.LENGTH_SHORT);
                        updateUI(null);
                    }
                }
            });
        }

    }

    public void updateUI(FirebaseUser myUser) {
        //user = mAuth.getCurrentUser();

        if (myUser != null) {

            UserProfileChangeRequest updateProfile = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(userName.getText().toString()).build();

            myUser.updateProfile(updateProfile).addOnCompleteListener(this, new OnCompleteListener<Void>() {

                @Override
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        verifyEmail(user);
                    } else {
                        Toast.makeText(getApplicationContext(), "Fail update !", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

    }

    private void verifyEmail(FirebaseUser myUser) {
        if (myUser != null) {
            myUser.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {

                @Override
                public void onComplete(Task<Void> task) {

                    if (task.isSuccessful()) {

                        Toast.makeText(getApplicationContext(), "Email send !", Toast.LENGTH_SHORT).show();
                       // mAuth.signOut();
                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));

                    } else {

                    }
                }
            });
        }

    }




}
