package Michal.Shefer.townumbers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class TTSService extends Service {

    TextToSpeechHelper ttsh = new TextToSpeechHelper();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ttsh.initTextToSpeech(this.getApplicationContext());
        String st = intent.getStringExtra("text");
        ttsh.speak(st);
        return START_NOT_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
       stopSelf();
    }

    @Override
    public void onDestroy() {
        ttsh.close();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }
}