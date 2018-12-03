package my.edu.taruc.lab41sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textViewName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private ImageView imageViewProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences(getString(R.string.pref_file),MODE_PRIVATE);
        String name;
        int gender; //-1 = default , 1= male, 0= female
        name = sharedPreferences.getString("name","");
        gender = sharedPreferences.getInt("gender",-1);
        textViewName.setText(name);
        if(gender == 1)
        {
            imageViewProfile.setBackgroundResource(R.drawable.male);
        }
        else if (gender == 0)
        {
            imageViewProfile.setBackgroundResource(R.drawable.female);
        }
        else
        {
            imageViewProfile.setBackgroundResource(R.drawable.profile);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //TODO: save shared preference
        //1. Create an instance of editor and Begin transaction
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //2. Read data from UI
        String name = textViewName.getText().toString();
        int gender = radioGroupGender.getCheckedRadioButtonId();
        //3. Write data to the shared preference
        editor.putString("name",name);
        if(gender==R.id.radioButtonMale)
        {
            editor.putInt("gender",1);
        }
        else if(gender==R.id.radioButtonFemale)
        {
            editor.putInt("gender",0);
        }
        else
        {
            editor.putInt("gender",-1);
        }
        //4. Commit or Apply changes
        //editor.commit();
        //OR
        editor.apply();
    }
}
