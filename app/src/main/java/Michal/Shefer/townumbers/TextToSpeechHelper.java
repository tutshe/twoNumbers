package Michal.Shefer.townumbers;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

public class TextToSpeechHelper {
    private static TextToSpeech textToSpeech;//עצם מהמחלקה של אנדרואיד
    //אתחול של עצם מסוג TextToSpeech
    public static void initTextToSpeech(Context context){
        if (textToSpeech==null){
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status==TextToSpeech.SUCCESS){
                        int result = textToSpeech.setLanguage(Locale.US);
                        if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                            Toast.makeText(context,"Text to Speech Language problem", Toast.LENGTH_LONG);
                        }else {
                            Toast.makeText(context,"Text to Speech Initialization Problem", Toast.LENGTH_LONG);
                        }
                    }
                }
            });
        }
    }
    //מקבלת טקסט ושולחת אותו לרמקול
    public static void speak(String text){
        if (textToSpeech!=null)
            textToSpeech.speak(text, textToSpeech.QUEUE_ADD,null,null);
    }
    //מורידה את האובייקט. סוגרת אותו
    public static void close(){
        if (textToSpeech!=null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech=null;
        }
    }
}
