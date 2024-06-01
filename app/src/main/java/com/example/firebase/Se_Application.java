package com.example.firebase;


import android.app.Application;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Se_Application extends Application {

    private static String TAG = "Se_Application";
    public static String Server_URL;
    public static Se_LocalDbConnector Localdb;
    public static AlphaAnimation ClickedAnimation = new AlphaAnimation(1F, 0.2F);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Se_Application onCreate called");
        this.Init_Value();
        Log.d(TAG, "Localdb initialized: " + (Localdb != null));
    }

    public void Init_Value() {
        Localdb = new Se_LocalDbConnector(this.getApplicationContext());
        try {
            FirebaseApp.initializeApp(this);
            Log.d(TAG, "Firebase initialized successfully");

            // Optionally, initialize other Firebase services
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

        } catch (Exception e) {
            Log.e(TAG, "Firebase initialization failed", e);
        };
    }


    public static boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

