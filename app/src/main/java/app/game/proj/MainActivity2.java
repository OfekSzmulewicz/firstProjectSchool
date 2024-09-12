package app.game.proj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    private String selectedOptionFrom = null; // Variable to hold the selected 'From' option
    private String selectedOptionTo = null; // Variable to hold the selected 'To' option
    // amount that the user enters
    private EditText amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String ShowName = "Hello " + Name + "!";
        TextView NameView = findViewById(R.id.NameView);
        NameView.setText(ShowName);

        String[] Currency= {"ILS", "USD", "EUR"}; // options for dropdown drawers "spinners"

        // Find the Spinner in the layout
        Spinner fromSpinner = findViewById(R.id.fromOptions);
        Spinner toSpinner = findViewById(R.id.toOptions);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Currency);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        // Initialize amountEditText by finding it in the layout
        amountEditText = findViewById(R.id.AmountInput); // Updated to "AmountInput"


        //set listeners that detect when the user selects an option and transfer the selected option to the variable
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOptionFrom = fromSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOptionTo = fromSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // find the 'convert' button in the layout and set a onClickListener
        Button convert = findViewById(R.id.convert);
        convert.setOnClickListener(view -> {
            // Call the convert method when the button is clicked
            convert(selectedOptionFrom, selectedOptionTo);
        });

        }

    // Method to handle conversion logic
    @SuppressLint("SetTextI18n")
    public void convert(String SOF, String SOT) {
        // Retrieve the amount entered by the user
        String amountText = amountEditText.getText().toString();


        // Validate that an amount has been entered
        if (amountText.isEmpty()) {
            Toast.makeText(MainActivity2.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse the amount to a double
        double amount = Double.parseDouble(amountText);

        double result = 0; // Variable to store the conversion result

        // Conversion logic based on selected currencies
        if (Objects.equals(SOF, "ILS")) {
            switch (SOT) {
                case "USD":
                    result = amount * 0.27; // Convert ILS to USD

                    break;
                case "EUR":
                    result = amount * 0.25; // Convert ILS to EUR

                    break;
                case "ILS":
                    result = amount; // No conversion needed if both are ILS

                    break;
            }
        } else if (Objects.equals(SOF, "USD")) {
            switch (SOT) {
                case "ILS":
                    result = amount * 3.70; // Convert USD to ILS

                    break;
                case "EUR":
                    result = amount * 0.93; // Convert USD to EUR

                    break;
                case "USD":
                    result = amount; // No conversion needed if both are USD

                    break;
            }
        } else if (Objects.equals(SOF, "EUR")) {
            switch (SOT) {
                case "ILS":
                    result = amount * 4.00; // Convert EUR to ILS

                    break;
                case "USD":
                    result = amount * 1.08; // Convert EUR to USD

                    break;
                case "EUR":
                    result = amount; // No conversion needed if both are EUR

                    break;
            }
        }
        // initialize the result editText
        TextView res = findViewById(R.id.result);
        res.setText(amount + " " + SOF + " equals " + result + " " + SOT +"!");
    }
}
