package ca.gbc.comp3074.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float number_of_hours, hourly_rate;
    double pay, tax;

    EditText amountHours;
    EditText hourRate;
    TextView total_Pay;
    TextView tax_Payed;

    Button calculate_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call for variable
        amountHours = (EditText) findViewById(R.id.no_of_hours);
        hourRate = (EditText) findViewById(R.id.hourly_rate);
        total_Pay = (TextView) findViewById(R.id.total_Pay);
        tax_Payed = (TextView) findViewById(R.id.tax_Payed);

        calculate_btn = (Button) findViewById(R.id.submit_btn);
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check if Fields are empty or not
                if(TextUtils.isEmpty(amountHours.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Input a Value", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(hourRate.getText().toString())){
                    Toast.makeText(MainActivity.this,"Please Input a Value", Toast.LENGTH_SHORT).show();
                }
                // If NOT empty
                else{
                    number_of_hours = Float.parseFloat(amountHours.getText().toString());
                    hourly_rate = Float.parseFloat(hourRate.getText().toString());

                    if(number_of_hours <= 40){
                        pay = number_of_hours * hourly_rate;
                    }
                    else{
                        pay = (number_of_hours - 40.0) * hourly_rate * 1.5 +(40.0 * hourly_rate);
                    }

                    tax = pay * 0.18;
                    total_Pay.setText("Total Pay: $ " + String.format("%.2f",pay));
                    tax_Payed.setText("Tax: $ "+ String.format("%.2f",tax));
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.about_menu:{
                openAbout();
            }
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}