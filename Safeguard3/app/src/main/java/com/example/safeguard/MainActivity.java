package com.example.safeguard;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure activity_main.xml exists
    }

    // Override the method to handle key press events
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Check if the tilde (~) key is pressed
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_GRAVE) {
            showPopup(); // Trigger the popup
            return true; // Indicate that the key event has been handled
        }
        return super.dispatchKeyEvent(event); // Pass other key events to the parent
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
            // E.g., Start an intent to place a call
            dialog.dismiss();
        });

        // Set up the "911" button behavior
        Button nineOneOneButton = popupView.findViewById(R.id.nineOneOneButton);
        nineOneOneButton.setOnClickListener(v -> {
            // Placeholder for 911 action
            // E.g., Start an intent to place a call
            dialog.dismiss();
        });

        // Show the dialog
        dialog.show();
    }
}
