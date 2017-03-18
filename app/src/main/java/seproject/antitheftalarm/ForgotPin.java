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

public class ForgotPin extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedpreferences;
    EditText etEmail;
    Button btnSendPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSendPin = (Button)findViewById(R.id.btnSendPin);
        btnSendPin.setOnClickListener(this);
    }
    private void sendEmail() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String email = sharedpreferences.getString("emailKey", "");
        final String pin = sharedpreferences.getString("passwordKey", "");
        String subject = "Anti-Theft Alarm";
        String message ="Your Pin: "+pin;
        final InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        String enteredEmail = etEmail.getText().toString();
                if(enteredEmail.equals(email)){
                    SendMail sm = new SendMail(this, email, subject, message);
                    sm.execute();
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    etEmail.getText().clear();
                }
                else {
                    etEmail.setError("Invalid!");
                }
    }

    @Override
    public void onClick(View v) {
        sendEmail();

    }
}
