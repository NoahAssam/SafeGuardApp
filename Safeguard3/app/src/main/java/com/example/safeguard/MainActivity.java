package com.example.safeguard;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView alarmIndicator;
    private boolean isAlarmOn = false; // Default state: OFF

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure activity_main.xml exists

        // Initialize the alarm indicator
        alarmIndicator = findViewById(R.id.alarmIndicator);
        updateAlarmUI();
    }

    // Override the method to handle key press events
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Check if the tilde (~) key is pressed
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_GRAVE) {
            toggleAlarm(); // Toggle the alarm
            showPopup();   // Trigger the popup
            return true;   // Indicate that the key event has been handled
        }
        return super.dispatchKeyEvent(event); // Pass other key events to the parent
    }

    // Method to toggle the alarm state
    private void toggleAlarm() {
        isAlarmOn = !isAlarmOn; // Toggle the alarm state
        updateAlarmUI();        // Update the UI based on the state
    }

    // Method to update the alarm UI
    private void updateAlarmUI() {
        if (isAlarmOn) {
            // Alarm ON state
            alarmIndicator.setText("ALARM: ON");
            alarmIndicator.setBackgroundColor(getResources().getColor(R.color.red, null)); // Red for ON
        } else {
            // Alarm OFF state
            alarmIndicator.setText("ALARM: OFF");
            alarmIndicator.setBackgroundColor(getResources().getColor(R.color.green, null)); // Green for OFF
        }
    }

    // Method to display the popup
    private void showPopup() {
        // Build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout for the popup
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.custom_popup, null); // Ensure custom_popup.xml matches your layout
        builder.setView(popupView);

        // Set up the dialog
        AlertDialog dialog = builder.create();

        // Set up the "Emergency Call" button behavior
        Button emergencyCallButton = popupView.findViewById(R.id.emergencyCallButton);
        emergencyCallButton.setOnClickListener(v -> {
            // Placeholder for Emergency Call action
            // Add your logic to initiate an emergency call
            dialog.dismiss();
        });

        // Set up the "911" button behavior
        Button nineOneOneButton = popupView.findViewById(R.id.nineOneOneButton);
        nineOneOneButton.setOnClickListener(v -> {
            // Placeholder for 911 action
            // Add your logic to initiate a 911 call
            dialog.dismiss();
        });

        // Show the dialog
        dialog.show();
    }
}
