package de.annette_gymnasium.arduinorobotjoystick;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jmedeisis.bugstick.Joystick;
import com.jmedeisis.bugstick.JoystickListener;

public class MainActivity extends AppCompatActivity {

    boolean nimmtAuf = false;
    BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialisierung der Views
        final TextView textView = (TextView) findViewById(R.id.textView);
        Joystick joystick = (Joystick) findViewById(R.id.joystick);
        Button buttonAufnehmen = (Button) findViewById(R.id.buttonAufnehmen);
        final Button buttonStick = (Button) findViewById(R.id.buttonStick);
        final GradientDrawable bgShape = (GradientDrawable) buttonStick.getBackground().getCurrent();


        if(BTAdapter == null) {
            new AlertDialog.Builder(this).setTitle("Nicht kompatibel")
                    .setMessage("Dein Handy unterstützt kein Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           System.exit(0);
                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


        //der Listener vom Joystick
        joystick.setJoystickListener(new JoystickListener() {
            @Override
            public void onDown() {
                textView.setText("onDown");
            }

            @Override
            public void onDrag(float degrees, float offset) {
                textView.setText("onDrag. degrees: " +degrees +"; offset: " +offset);
            }

            @Override
            public void onUp() {
                textView.setText("onUp");
            }
        });

        buttonAufnehmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgShape.setColor(Color.parseColor("#E53935"));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bgShape.setColor(Color.parseColor("#80CBC4"));
                    }
                },1000);
            }
        });
    }

    void makroAufnehmen() {

    }
}
