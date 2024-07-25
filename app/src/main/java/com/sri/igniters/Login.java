package com.sri.igniters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private View ivLogo;
    private TextInputLayout login_name, login_password;
    private TextInputEditText editTextName, editTextPassword;
    private MaterialButton loginbt;

    //already logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
//            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //firebase instance
        mAuth=FirebaseAuth.getInstance();
        //variables from xml
        editTextName = findViewById(R.id.name);
        editTextPassword = findViewById(R.id.password);
        loginbt = findViewById(R.id.login_bt);
        ivLogo = findViewById(R.id.logo);
        login_name = findViewById(R.id.login_name);
        login_password = findViewById(R.id.login_password);

        // Trigger animations
        animateLoginScreen();

//        login button listener
        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass;
                email = String.valueOf(editTextName.getText());
                pass = String.valueOf(editTextPassword.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Login.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    private void animateLoginScreen() {
        ivLogo.setAlpha(0f);
        ivLogo.setTranslationY(-50f);
        login_name.setAlpha(0f);
        login_name.setTranslationX(-50f);
        login_password.setAlpha(0f);
        login_password.setTranslationX(50f);
        loginbt.setAlpha(0f);
        loginbt.setScaleX(0.8f);
        loginbt.setScaleY(0.8f);

        ivLogo.animate().alpha(.8f).translationY(0f).setDuration(1000).start();
        login_name.animate().alpha(1f).translationX(0f).setDuration(1000).setStartDelay(300).start();
        login_password.animate().alpha(1f).translationX(0f).setDuration(1000).setStartDelay(500).start();
        loginbt.animate().alpha(.7f).scaleX(1f).scaleY(1f).setDuration(1000).setStartDelay(700).start();
    }
}