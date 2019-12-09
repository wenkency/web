package cn.carhouse.websample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.carhouse.web.utils.WebConfig;
import cn.carhouse.web.utils.WebUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 修改默认配置
        WebConfig.getInstance().setProgressHeight(5);
        // 修改进度条颜色
        WebConfig.getInstance().setProgressDrawable(R.drawable.progress_bar_test);
    }

    public void web(View view) {
        // 使用方式
        WebUtils.getInstance().startWebActivity(this, "https://www.baidu.com/");
    }
}
