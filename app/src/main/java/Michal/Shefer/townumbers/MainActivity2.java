package Michal.Shefer.townumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    Intent TTSService;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = this.getApplicationContext();
        //RadioButtons
        RadioGroup rbGroup;
        rbGroup = findViewById(R.id.rbGroup);
        // This overrides the radiogroup onCheckListener
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    Toast.makeText(MainActivity2.this, "RB " +checkedRadioButton.getText()+ " is checked", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //CheckBox הדגמה
        CheckBox cbxSound = findViewById(R.id.cbxSound);
        cbxSound.setChecked(true);
        cbxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Checkbox is checked
                    Toast.makeText(MainActivity2.this, "Checkbox is checked", Toast.LENGTH_SHORT).show();
                } else {
                    // Checkbox is unchecked
                    Toast.makeText(MainActivity2.this, "Checkbox is unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Switch הדגמה
        androidx.appcompat.widget.SwitchCompat swWiFi = findViewById(R.id.swWiFi);
        swWiFi.setChecked(false);
        swWiFi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Checkbox is checked
                    Toast.makeText(MainActivity2.this, "Wi Fi is connected", Toast.LENGTH_SHORT).show();
                } else {
                    // Checkbox is unchecked
                    Toast.makeText(MainActivity2.this, "Wi Fi is not connected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Spinner הדגמה
        //יצירת רשימה
        ArrayList<String> options = new ArrayList<String>();

        options.add("<");
        options.add(">");
        options.add("=");
        options.add("Please Choose");

        Spinner spinner = findViewById(R.id.spinner);

        //יצירת מתאם
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        options);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        //ברירת מחדל בקוד
        spinner.setSelection(3);

        Random random = new Random();
        int randomNumber = random.nextInt(1001);
        TextView tvNum1 = findViewById(R.id.tvNum1);
        tvNum1.setText(""+randomNumber);
        randomNumber = random.nextInt(1001);
        TextView tvNum2 = findViewById(R.id.tvNum2);
        tvNum2.setText(""+randomNumber);
        TextView tvAnnounce = findViewById(R.id.tvAnnounce);

        //מאזין שפועל כשהבחירה משתנה
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //  לקבל את הבחירה הנוכחית מהספינר
                String text = spinner.getSelectedItem().toString();
                boolean flag = false;
                switch (text){
                    case "<":
                        flag = Integer.parseInt(tvNum1.getText().toString()) < Integer.parseInt(tvNum2.getText().toString());
                        break;
                    case ">":
                        flag = Integer.parseInt(tvNum1.getText().toString()) > Integer.parseInt(tvNum2.getText().toString());
                        break;
                    case "=":
                        flag = Integer.parseInt(tvNum1.getText().toString()) == Integer.parseInt(tvNum2.getText().toString());
                        break;}
                if(flag) tvAnnounce.setText("Success");
                else if (text=="Please Choose") tvAnnounce.setText("");
                else tvAnnounce.setText("Try Again");
                TTSService = new Intent(context, TTSService.class);
                TTSService.putExtra("text", tvAnnounce.getText().toString());
                startService(TTSService);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.retToIntro) {
            //Back to intro activity
            Intent intent = new Intent( MainActivity2.this , MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id ==R.id.closeApp){
            finishAffinity(); // This will close all activities
        }
        return super.onOptionsItemSelected(item);
    }

}