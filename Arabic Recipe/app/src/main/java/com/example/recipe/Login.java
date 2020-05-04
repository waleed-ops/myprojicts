package com.example.recipe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText etemail,etpassword;
    private String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);

    }
    public void redirctsignuo(View view){

        startActivity(new Intent( Login.this,Sign_up.class));
    }

    private void redirct (){
        startActivity(new Intent( Login.this,MainActivity.class));
finish();
    }
    public void btnloginClick(View view){

        email =etemail.getText().toString().trim();
        password =etpassword.getText().toString().trim();

        if(email.equals("")){
            etemail.setError("Enter email");
        }
        else if (password.equals("")){
            etpassword.setError("Enter a password");
        }else
            {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                redirct();
                            }else{
                                Toast.makeText(Login.this,"Login Failed : "+task.getException(),Toast.LENGTH_SHORT);
                            }
                        }
                    });

        }


    }
}
