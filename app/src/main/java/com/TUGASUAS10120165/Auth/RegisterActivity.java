package com.TUGASUAS10120165.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.TUGASUAS10120165.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText email,password;
    private Button btn_register;
    private TextView txt_login;
    private ImageView back;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);
        txt_login = findViewById(R.id.txt_login);
        back = findViewById(R.id.back);
        auth =FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle(getString(R.string.titleProgress));
        progressDialog.setMessage(getString(R.string.IsiProgress));
        progressDialog.setCancelable(true);

        back.setOnClickListener(v ->{
            finish();
        });

        txt_login.setOnClickListener(v -> {
            finish();
        });

        btn_register.setOnClickListener(v -> {

            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (TextUtils.isEmpty(Email)) {
                email.setError("Email harus diisi!");
            } else if (TextUtils.isEmpty(Password)) {
                password.setError("Password harus diisi!");
            } else if (Password.length() < 6) {
                password.setError("Password minimal 6 karakter!");
            } else {
                register(Email,Password);
            }



        });

    }

    public void register(String email,String password){
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User dengan Email dan Password berhasil dibuat", Toast.LENGTH_SHORT).show();
                    reload();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Otentikasi Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){

        startActivity(new Intent(getApplicationContext(),Loginctivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}