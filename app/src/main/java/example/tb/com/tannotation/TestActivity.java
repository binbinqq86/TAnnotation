package example.tb.com.tannotation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import example.tb.com.module_annotation.FindId;
import example.tb.com.module_annotation.OnClick;
import example.tb.com.module_api.TAHelper;
import example.tb.com.module_api.TAHelper2;

/**
 * @auther tb
 * @time 2018/3/23 下午2:13
 * @desc
 */
@FindId(R.layout.activity_test)
public class TestActivity extends Activity {
    @FindId(R.id.tv)
    TextView tv;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TAHelper.inject(this);
        TAHelper2.inject(this);
        if(tv!=null){
            tv.setText("hahaha,how are you?");
        }
    }
    
    @OnClick({R.id.tv})
    void clickTest(View view){
        switch (view.getId()){
            case R.id.tv:
                Toast.makeText(TestActivity.this,"test click",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
