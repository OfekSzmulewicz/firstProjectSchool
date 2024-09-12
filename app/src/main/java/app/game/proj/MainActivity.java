package app.game.proj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText editText = findViewById(R.id.username); // reference to the username entering field
        Button button = findViewById(R.id.login_button);
        button.setOnClickListener(v -> {
            CharSequence text = editText.getText(); // get the username in a variable
            String Name = text.toString(); // convert to string for easier use in dialog
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("Name",Name);
            startActivity(intent);
        });

    }
}