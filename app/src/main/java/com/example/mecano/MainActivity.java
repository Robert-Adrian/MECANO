package com.example.mecano;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

//facebook libraries
import com.facebook.login.widget.LoginButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.AccessToken;

//google libraries
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.GoogleAuthProvider;
//import com.google.firebase.auth.R;


public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;
    public static final String TAG = "SignInActivity";
    private LoginButton facebookButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private CallbackManager callbackManager = CallbackManager.Factory.create();
    private GoogleSignIn mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();



        facebookButton = (LoginButton) findViewById(R.id.button3);
        facebookButton.setReadPermissions("email", "public_profile");
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>(){

            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }

        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn == true) {
            Intent intent = new Intent(this, MecanoMainActivity.class);
            startActivity(intent);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn == true) {
            Intent intent = new Intent(this, MecanoMainActivity.class);
            startActivity(intent);
        }

    }



    /**Sign up activity**/
    public void signActivity(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    /**Login activity**/
    public void loginActivity(View view) {
        EditText userName = (EditText) findViewById(R.id.editText2);
        EditText userPwd = (EditText) findViewById(R.id.editText);

        if (userName.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter an e-mail !", Toast.LENGTH_SHORT).show();

        } else if (userPwd.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter an password !", Toast.LENGTH_SHORT).show();
        } else {

            user = mAuth.getCurrentUser();

            if (user.getDisplayName().equals(userName.getText().toString().trim())) {

                mAuth.signInWithEmailAndPassword(userName.getText().toString(), userPwd.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Log In success !", Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(getApplicationContext(), "Log In failed !", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }

                });

            } else {
                Toast.makeText(getApplicationContext(), "User: " + userName.getText().toString() + " don't exist!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void updateUI(FirebaseUser myUser) {
//        myUser = mAuth.getCurrentUser();
       // Toast.makeText(getApplicationContext(), myUser.isEmailVerified().toString(), Toast.LENGTH_SHORT);
        if (myUser != null) {

            if (myUser.isEmailVerified()) {
                Toast.makeText(getApplicationContext(), "Hello !", Toast.LENGTH_SHORT);
                Intent intent = new Intent(this, MecanoMainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Your email is not verified !", Toast.LENGTH_SHORT).show();

            }

        }
    }


}
