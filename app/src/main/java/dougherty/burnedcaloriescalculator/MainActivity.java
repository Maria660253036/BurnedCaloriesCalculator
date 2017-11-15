package dougherty.burnedcaloriescalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener,
        OnSeekBarChangeListener, OnItemSelectedListener {

    // define the widgets
    private EditText weightET;
    private TextView milesRanTV;
    private SeekBar milesRanSeeker;
    private TextView caloriesBurnedTV;
    private Spinner feetSpinner;
    private Spinner inchesSpinner;
    private TextView bmiTV;
    private EditText nameET;

    // define instance variables
    private int split = 1;
    private int weight;
    private int miles;
    private int feet;
    private int inches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to the widgets
        weightET = (EditText) findViewById(R.id.weightET);
        milesRanTV = (TextView) findViewById(R.id.milesRanTV);
        milesRanSeeker = (SeekBar) findViewById(R.id.milesRanSeeker);
        caloriesBurnedTV = (TextView) findViewById(R.id.caloriesBurnedTV);
        feetSpinner = (Spinner) findViewById(R.id.feetSpinner);
        inchesSpinner = (Spinner) findViewById(R.id.inchesSpinner);
        bmiTV = (TextView) findViewById(R.id.bmiTV);
        nameET = (EditText) findViewById(R.id.nameET);

        // set up the listeners for the widgets
        weightET.setOnEditorActionListener(this);
        milesRanSeeker.setOnSeekBarChangeListener(this);
        feetSpinner.setOnItemSelectedListener(this);
        inchesSpinner.setOnItemSelectedListener(this);
        nameET.setOnEditorActionListener(this);

        // set array adapter for spinner widgets
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.feet_array, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        feetSpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.inches_array, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        inchesSpinner.setAdapter(adapter2);
    }

    public void calculateBMI() {

        float caloriesBurned = (float) (0.75 * weight * miles);

        float bmi = (weight * 703) / ((12 * feet + inches) * (12 * feet + inches));


    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        calculateBMI();
        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        milesRanTV.setText(progress + "mi");
        calculateBMI();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        calculateBMI();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        split = position + 1;
        calculateBMI();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
