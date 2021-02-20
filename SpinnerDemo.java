import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.memorydemo.R;

public class SpinnerDemo extends Activity {
    private static final String[] countries = {"北京", "上海", "广州", "深圳", "成都", "杭州"};

    private TextView mTextView;
    private Spinner mSpinner;
    private ArrayAdapter<String> mAdapter;
    private Animation mAnimation;

    @Override
    protected void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
        setContentView(R.layout.spinner_demo);

        mTextView = findViewById(R.id.textView9);
        mSpinner = findViewById(R.id.spinner);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);

        // 自定义的下拉视图布局样式
        mAdapter.setDropDownViewResource(R.layout.spinner_drop_down);

        // 设置数据的适配器
        mSpinner.setAdapter(mAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextView.setText("你选择的是：" + countries[position]);

                // 一定要设置父视图可见，否则 在选择后，Spinner会消失
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);
        mSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.startAnimation(mAnimation);
                return false;
            }
        });
    }
}
