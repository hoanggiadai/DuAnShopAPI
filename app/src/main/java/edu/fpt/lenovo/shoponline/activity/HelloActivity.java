package edu.fpt.lenovo.shoponline.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

import edu.fpt.lenovo.shoponline.R;
import edu.fpt.lenovo.shoponline.ultil.CheckConnection;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello);
        Timer timer = new Timer();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
        else {
            CheckConnection.ShowToastLong(getApplicationContext(), "Không có mạng !");
        }
    }
}