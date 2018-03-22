package example.tb.com.tannotation;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@BindId(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindId(R.id.tv)
    private TextView tv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindIdApi.bindId2(this);
        BindOnClick.bindOnClick(this);
        tv.setText("this is a test~");
    }
    
    @OnClick(R.id.tv)
    private void click(View view){
        switch (view.getId()){
            case R.id.tv:
                Toast.makeText(this,tv.getText().toString(),Toast.LENGTH_LONG).show();
                break;
        }
    }
}
