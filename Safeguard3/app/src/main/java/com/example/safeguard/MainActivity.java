package com.example.safeguard;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView alarmIndicator;
    private boolean isAlarmOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the alarm indicator
        alarmIndicator = findViewById(R.id.alarmIndicator);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the TILDE key (KEYCODE_GRAVE) is pressed
        if (keyCode == KeyEvent.KEYCODE_GRAVE) {
            toggleAlarm(); // Call the toggle method
            return true; // Indicate that the key event has been handled
        }
        return super.onKeyDown(keyCode, event);
    }

    private void toggleAlarm() {
        if (isAlarmOn) {
            // Turn OFF the alarm
            alarmIndicator.setText("ALARM: OFF");
            alarmIndicator.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            // Turn ON the alarm
            alarmIndicator.setText("ALARM: ON");
            alarmIndicator.setBackgroundColor(getResources().getColor(R.color.red));
        }
        isAlarmOn = !isAlarmOn; // Toggle the state
    }
}
