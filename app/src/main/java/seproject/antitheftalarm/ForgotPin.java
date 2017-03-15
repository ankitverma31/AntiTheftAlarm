package seproject.antitheftalarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static seproject.antitheftalarm.SetPin.MyPREFERENCES;

public class ForgotPin extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    EditText etEmail;
    Button btnSendPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pin);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String email = sharedpreferences.getString("emailKey", "");
        final String pin = sharedpreferences.getString("passwordKey", "");

        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSendPin = (Button)findViewById(R.id.btnSendPin);
//        Boolean online = isOnline();
        final InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        btnSendPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredEmail = etEmail.getText().toString();
                if(enteredEmail.equals(email)){
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    Toast.makeText(ForgotPin.this, "Old Pin: "+pin, Toast.LENGTH_LONG).show();

//                    Toast.makeText(ForgotPin.this, "Password sent to "+email, Toast.LENGTH_SHORT).show();
                    etEmail.getText().clear();
                }
                else {
                    etEmail.setError("Invalid!");
                }
            }
        });

//        Toast.makeText(ForgotPin.this, "Email"+email, Toast.LENGTH_SHORT).show();
    }
//    public boolean isOnline() {
//        ConnectivityManager cm =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//            return true;
//        }
//        return false;
//    }
}
