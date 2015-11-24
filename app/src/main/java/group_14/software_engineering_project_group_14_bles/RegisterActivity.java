package group_14.software_engineering_project_group_14_bles;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends Activity {

    EditText USER_NAME,USER_PASS,CON_PASS;
    String user_name,user_pass,con_pass;
    Button REG;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        USER_NAME = (EditText) findViewById(R.id.reg_user);
        USER_PASS = (EditText) findViewById(R.id.reg_pass) ;
        CON_PASS = (EditText) findViewById(R.id.con_pass);
        REG = (Button) findViewById(R.id.user_reg);

        REG.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                user_name = USER_NAME.getText().toString();
                user_pass = USER_PASS.getText().toString();
                con_pass = CON_PASS.getText().toString();

                if(!(user_pass.equals(con_pass)))
                {
                    Toast.makeText(getBaseContext(), "Passwords are not matching", Toast.LENGTH_LONG).show();
                    USER_NAME.setText("");
                    USER_PASS.setText("");
                    CON_PASS.setText("");
                }
                else
                {
                    Calendar now = Calendar.getInstance();
                    Integer year = now.get(Calendar.YEAR);
                    Integer month = now.get(Calendar.MONTH);
                    Integer date = now.get(Calendar.DATE);
                    String userID = year.toString()+month.toString()+date.toString();

                    UserDbHelper userDbHelper = new UserDbHelper(context);
                    userDbHelper.addUserInformations(userDbHelper,userID,user_name, user_pass);
                    Toast.makeText(getBaseContext(), "Registration success", Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });



    }

}
