package example.tb.com.tannotation;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

@BindId(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindId(R.id.tv)
    private TextView tv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindIdApi.bindId2(this);
        tv.setText("this is a test~");
    }
}
