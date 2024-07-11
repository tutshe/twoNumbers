package Michal.Shefer.townumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bPlay;
    TextView tvTitle;
    //TextToSpeechHelper ttsh;
    Intent TTSService;
    private void startTTSService(String text){
        TTSService = new Intent(this.getApplicationContext(), TTSService.class);
        TTSService.putExtra("text", text);
        startService(TTSService);

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ttsh = new TextToSpeechHelper();
        //ttsh.initTextToSpeech(this.getApplicationContext());
        startTTSService("");
        setContentView(R.layout.activity_main);
        tvTitle = findViewById(R.id.tvTitle);
        bPlay = findViewById(R.id.bPlay);

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 tvTitle.setTextColor(Color.parseColor("#FFF123"));
                Intent intent = new Intent( MainActivity.this , LoginScreen.class);
                //ttsh.speak("Next");
                startTTSService("Next");
                startActivity(intent);
            }

        });
    }
}
//Intent go = new Intent(MainActivity.this, MainActivity2.class);
// startActivity (go);
