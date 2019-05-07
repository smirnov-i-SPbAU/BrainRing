package ru.spbhse.brainring.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.spbhse.brainring.R;

public class SelectLocalGameModeActivity extends AppCompatActivity {

    private int firstValue = 20;
    private int secondValue = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_game_settings);

        SeekBar firstBar = findViewById(R.id.firstCounterBar);
        firstBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                firstValue = 10 + progress * 10;
                reloadValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar secondBar = findViewById(R.id.secondCounterBar);
        secondBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                secondValue = 10 + progress * 10;
                reloadValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button juryButton = findViewById(R.id.juryModeButton);
        juryButton.setOnClickListener(v -> {
            Intent intent = new Intent(SelectLocalGameModeActivity.this, JuryActivity.class);
            intent.putExtra("firstTimer", firstValue);
            intent.putExtra("secondTimer", secondValue);
            startActivity(intent);
        });

        Button greenButton = findViewById(R.id.greenPlayerButton);
        greenButton.setOnClickListener(v -> {
            Intent intent = new Intent(SelectLocalGameModeActivity.this, PlayerActivity.class);
            intent.putExtra("color", "green");
            startActivity(intent);
        });

        Button redButton = findViewById(R.id.redPlayerButton);
        redButton.setOnClickListener(v -> {
            Intent intent = new Intent(SelectLocalGameModeActivity.this, PlayerActivity.class);
            intent.putExtra("color", "red");
            startActivity(intent);
        });
    }

    private void reloadValues() {
        TextView first = findViewById(R.id.firstCounterValue);
        first.setText(String.valueOf(firstValue));
        TextView second = findViewById(R.id.secondCounterValue);
        second.setText(String.valueOf(secondValue));
    }
}
