# Web
快速打开一个Web连接页面。
### 引入

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

android {
    ...
    defaultConfig {
        ...
        // 不要改这里
        ndk {
            abiFilters "armeabi", "armeabi-v7a", "x86", "mips"
        }
    }
}

implementation 'com.github.wenkency:web:1.0.0'

```

### 使用方式
```
      // 修改默认配置
      WebConfig.getInstance().setProgressHeight(5);
      // 修改进度条颜色
      WebConfig.getInstance().setProgressDrawable(R.drawable.progress_bar_test);
      // 打开方式
      1. WebUtils.getInstance().startWebActivity(Context context, String url) ;
      2. WebUtils.getInstance().startWebActivity(Context context, String url, String title) ;
      3. WebUtils.getInstance().startWebActivity(Context context, WebData data) ;

      public class WebData implements Serializable {
          public String url;
          public String title;
      }
```

### 运行结果

<img src="screenshot/image.jpg" width="360px"/>
