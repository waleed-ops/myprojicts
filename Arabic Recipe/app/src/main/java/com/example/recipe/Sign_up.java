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

public class Sign_up extends AppCompatActivity {
private EditText etemail,etpassword,etconfirm;
private String email,password,confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up);
        etemail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);
        etconfirm = findViewById(R.id.etconfirm);


    }


    public void btnSignupClick(View view){

        email =etemail.getText().toString().trim();
        password =etpassword.getText().toString().trim();
        confirm =etconfirm.getText().toString().trim();

        if(email.equals("")){
            etemail.setError("Enter email");
        }
        else if (password.equals("")){
            etpassword.setError("Enter a password");
        }
        else if (confirm.equals("")){
            etconfirm.setError("Confirm Password");
        }
        else if (!confirm.equals(password)){
            etconfirm.setError("confirm password correctly");
        }
        else{
             FirebaseAuth mAuth=FirebaseAuth.getInstance();
             mAuth.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Sign_up.this,"User created successfuly",Toast.LENGTH_SHORT);
                            startActivity(new Intent(Sign_up.this,Login.class));
                            finish();
                            }else{
                                Toast.makeText(Sign_up.this,"Faild to creat User : "+task.getException(),Toast.LENGTH_SHORT);
                            }
                         }
                     });

        }



    }
}
