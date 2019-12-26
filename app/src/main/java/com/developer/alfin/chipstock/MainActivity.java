package com.developer.alfin.chipstock;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Animation slidBtm, slidBtm1, slidTop;
    CardView cardLogin;
    LinearLayout layLogin;
    ImageView topImg, btmImg, LogoApp;
    EditText inUser, inPass;
    TextView tx_Logo, txSignUp;
    Button btnSend;
    ProgressDialog dialog;
    String user = "admin123@gmail.com";
    String pass = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper = new DatabaseHelper(this);

        slidBtm = AnimationUtils.loadAnimation(this, R.anim.slide_btm);
        slidBtm1 = AnimationUtils.loadAnimation(this, R.anim.slide_btm1);
        slidTop = AnimationUtils.loadAnimation(this, R.anim.slide_top);
        topImg = findViewById(R.id.img_top);
        btmImg = findViewById(R.id.img_btm);
        LogoApp = findViewById(R.id.logo_splash);
        tx_Logo = findViewById(R.id.tx_logo);
        cardLogin = findViewById(R.id.card_login);
        layLogin = findViewById(R.id.layout_login);
        inUser = findViewById(R.id.email_edt);
        inPass = findViewById(R.id.pass_edt);
        btnSend = findViewById(R.id.btn_Send);
        dialog = new ProgressDialog(this);

        splashScreen();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginAdmin();
            }
        });


    }

    private void loginAdmin() {
        String email = inUser.getText().toString();
        String password = inPass.getText().toString();

        dialog.setTitle("Login Admin");
        dialog.setMessage("Harap tunggu..");
        dialog.show();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(MainActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } if (!isEmailValid(email)) {
            Toast.makeText(MainActivity.this, "Email Tidak valid", Toast.LENGTH_SHORT).show();
        }
        else {

            if (email.equals(user) && password.equals(pass)) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));

            } else {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Email dan Password yang anda masukkan salah", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void splashScreen() {

        btmImg.startAnimation(slidBtm);
        topImg.startAnimation(slidTop);

        LogoApp.animate().translationY(140).alpha(0).setDuration(1000).setStartDelay(2000);
        tx_Logo.animate().translationY(140).alpha(0).setDuration(1000).setStartDelay(2000);

        cardLogin.startAnimation(slidBtm1);
        layLogin.startAnimation(slidBtm1);

    }


        public static boolean isEmailValid(String email) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
}
