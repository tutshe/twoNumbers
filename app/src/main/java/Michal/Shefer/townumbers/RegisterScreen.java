package Michal.Shefer.townumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {
    EditText etRegName,etRegEmail,etRegPassword,etPhoneNo;
    Button btRegister;
    HelperDb helperDB= new HelperDb(this);
    SQLiteDatabase db;
    UserDtails user = new UserDtails();
    ContentValues cv = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        etRegName = (EditText)findViewById(R.id.etName);
        etRegEmail = (EditText)findViewById(R.id.etEmailAddress);
        etRegPassword= (EditText)findViewById(R.id.etPassword);
        etPhoneNo  = (EditText)findViewById(R.id.etPhone);
        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
         public void onClick(View view) {
            user.setUserName(etRegName.getText().toString());
            user.setUserEmail(etRegEmail.getText().toString());
            user.setUserPwd(etRegPassword.getText().toString());
            user.setUserPhone(etPhoneNo.getText().toString());

            cv.put(helperDB.USER_NAME,user.getUserName());
            cv.put(helperDB.USER_EMAIL,user.getUserEmail());
            cv.put(helperDB.USER_PWD,user.getUserPwd());
            cv.put(helperDB.USER_PHONE,user.getUserPhone());
            db = helperDB.getWritableDatabase();
            db.insert(helperDB.USERS_TABLE,null,cv);
            db.close();
            Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
            startActivity(intent);
         }
        });

    }
}