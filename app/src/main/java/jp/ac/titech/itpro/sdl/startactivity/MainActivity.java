package jp.ac.titech.itpro.sdl.startactivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String ACTION_INPUT = "jp.ac.titech.itpro.sdl.ACTION_INPUT";
    private final static String NAME_EXTRA = "name";
    private final static int REQ_NAME_2 = 1234;
    private final static int REQ_NAME_3 = 1235;

    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.main_title);
        title.setText(TAG);

        answer = findViewById(R.id.main_answer);

        final EditText input = findViewById(R.id.main_input);

        Button buttonGo1 = findViewById(R.id.main_button_go1);
        buttonGo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick - Go 1");
                String name = input.getText().toString().trim();
                if (!name.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                    intent.putExtra(AnswerActivity.NAME_EXTRA, name);
                    startActivity(intent);
                }
            }
        });

        Button buttonGo2 = findViewById(R.id.main_button_go2);
        buttonGo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick - Go 2");
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(intent, REQ_NAME_2);
            }
        });

        Button buttonGo3 = findViewById(R.id.main_button_go3);
        buttonGo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick - Go 3");
                Intent intent = new Intent(ACTION_INPUT);
                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (!activities.isEmpty()) {
                    startActivityForResult(intent, REQ_NAME_3);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_no_activities_format, ACTION_INPUT),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        Log.d(TAG, "onActivityResult");
        switch (reqCode) {
            case REQ_NAME_2:
                if (resCode == RESULT_OK) {
                    String name = data.getStringExtra(InputActivity.NAME_EXTRA);
                    if (name != null && !name.isEmpty()) {
                        answer.setText(getString(R.string.answer_format, name));
                    }
                } else {
                    answer.setText(R.string.answer_receive_default);
                }
                break;
            case REQ_NAME_3:
                if (resCode == RESULT_OK) {
                    String name = data.getStringExtra(NAME_EXTRA);
                    if (name != null && !name.isEmpty()) {
                        answer.setText(getString(R.string.answer_format, name));
                    }
                } else {
                    answer.setText(R.string.answer_receive_default);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
