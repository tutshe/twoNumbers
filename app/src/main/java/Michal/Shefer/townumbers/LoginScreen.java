package Michal.Shefer.townumbers;

import static android.app.DownloadManager.COLUMN_ID;
import static com.google.android.material.internal.ContextUtils.getActivity;
import static Michal.Shefer.townumbers.HelperDb.USERS_TABLE;
import static Michal.Shefer.townumbers.HelperDb.USER_EMAIL;
import static Michal.Shefer.townumbers.HelperDb.USER_NAME;
import static Michal.Shefer.townumbers.HelperDb.USER_PHONE;
import static Michal.Shefer.townumbers.HelperDb.USER_PWD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {
    EditText etRegName, etRegEmail, etRegPassword, etRepPassword, etPhoneNo;
    Button btLogin, btGoReg;
    HelperDb helperDB = new HelperDb(this);
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        etRegName = (EditText) findViewById(R.id.editTextText);
        etRegPassword = (EditText) findViewById(R.id.editTextTextPassword);
        btGoReg = findViewById(R.id.btGoReg);
        btGoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<UserDtails> list = getAllRecords();
//                    Intent intent = new Intent(getActivity(), ListActivity.class);
//                    intent.putExtra("name",user.getUserName());
//                    startActivity(intent);
            }
        });
    }

    public ArrayList<UserDtails> getAllRecords() {
        int index;
        String pwd, name, phone, email;
        db = helperDB.getReadableDatabase();
        ArrayList<UserDtails> list = new ArrayList<>();
        Cursor cursor = db.query(HelperDb.USERS_TABLE, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            index = cursor.getColumnIndex(USER_NAME);
            name = cursor.getString(index);
            index = cursor.getColumnIndex(USER_PWD);
            pwd = cursor.getString(index);
            index = cursor.getColumnIndex(USER_EMAIL);
            email = cursor.getString(index);
            index = cursor.getColumnIndex(USER_PHONE);
            phone = cursor.getString(index);
            UserDtails record = new UserDtails(name, pwd, email, phone);
            list.add(record);
        }
        db.close();
        return list;
    }
    public void deleteUserByRow(long id)
    {
        db = helperDB.getWritableDatabase();
        db.delete(USERS_TABLE, COLUMN_ID + " = " + id, null);
        db.close(); // close the database
    }

}
//                user.setUserName(etRegName.getText().toString());
//                user.setUserEmail(etRegEmail.getText().toString());
//                user.setUserPwd(etRegPassword.getText().toString());
//                user.setUserPhone(etPhoneNo.getText().toString());
//
//                cv.put(USER_NAME, user.getUserName());
//                cv.put(USER_EMAIL, user.getUserEmail());
//                cv.put(USER_PWD, user.getUserPwd());
//                cv.put(USER_PHONE, user.getUserPhone());
//    db = helperDB.getWritableDatabase();
//                db.insert(helperDB.USERS_TABLE, null, cv);
//                db.close();
