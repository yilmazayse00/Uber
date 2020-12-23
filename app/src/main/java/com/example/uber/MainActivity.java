package com.example.uber;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final Button btnOtp = findViewById(R.id.getOtp);
         final EditText inputMobile = findViewById(R.id.editTextPhone);
         final ProgressBar progressBar = findViewById(R.id.progressBar);

        btnOtp.setOnClickListener(v -> {
             if (inputMobile.getText().toString().trim().isEmpty()){
                 Toast.makeText(MainActivity.this, "Geçerli bir telefon numarası giriniz.", Toast.LENGTH_SHORT).show();
                 return;
             }
             progressBar.setVisibility(View.VISIBLE);
             btnOtp.setVisibility(View.INVISIBLE);

             PhoneAuthProvider.getInstance().verifyPhoneNumber(
                     "+90" + inputMobile.getText().toString(),
                     60,
                     TimeUnit.SECONDS,
                     MainActivity.this,
                     new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                         @Override
                         public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                             progressBar.setVisibility(View.GONE);
                             btnOtp.setVisibility(View.VISIBLE);
                         }

                         @Override
                         public void onVerificationFailed(@NonNull FirebaseException e) {
                             progressBar.setVisibility(View.GONE);
                             btnOtp.setVisibility(View.VISIBLE);
                             Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                         }

                         @Override
                         public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                             progressBar.setVisibility(View.GONE);
                             btnOtp.setVisibility(View.VISIBLE);
                             Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                             intent.putExtra("mobile", inputMobile.getText().toString());
                             intent.putExtra("verificationId",verificationId);
                             startActivity(intent);
                         }
                     });

         });

    }
}
