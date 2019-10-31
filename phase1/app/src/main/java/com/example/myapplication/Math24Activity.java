package com.example.myapplication;

    import androidx.appcompat.app.AppCompatActivity;
        import com.example.myapplication.R;
        import android.os.Bundle;
        import android.view.View;
        import androidx.appcompat.app.AppCompatActivity;
        import android.widget.Button;
        import android.content.Intent;


public class Math24Activity extends AppCompatActivity implements View.OnClickListener {
    private Button plus, minor, multiply, divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math24);

        plus = findViewById(R.id.btn_plus);
        plus.setOnClickListener(this);
        minor = findViewById(R.id.btn_minor);
        minor.setOnClickListener(this);
        multiply = findViewById(R.id.btn_multiply);
        multiply.setOnClickListener(this);
        divide = findViewById(R.id.btn_divide);
        divide.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_plus:

//                startActivity();
                break;
            case R.id.btn_minor:

//                startActivity();
                break;
            case R.id.btn_multiply:

//                startActivity();
                break;
            case R.id.btn_divide:

                break;
        }
    }
}