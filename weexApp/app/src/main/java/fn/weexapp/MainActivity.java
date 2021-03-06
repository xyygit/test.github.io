package fn.weexapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXViewUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RelativeLayout viewGroup;
    private static final String DEFAULT_IP = "http://jasongan.cn/dist/main.js";
    private static String CURRENT_IP= DEFAULT_IP; // your_current_IP
    private static final String WEEX_INDEX_URL = "http://jasongan.cn/dist/main.js";
//    private static final String WEEX_INDEX_URL = "file:///android_asset/index.js";

    private WXSDKInstance mWXSDKInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewGroup = (RelativeLayout)findViewById(R.id.activity_main);
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                viewGroup.addView(view);
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
        renderPage(mWXSDKInstance,getPackageName(),WEEX_INDEX_URL /*WXFileUtils.loadFileContent("main.js",this)*/,WEEX_INDEX_URL,null);
    }

    private void renderPage(WXSDKInstance mInstance,String packageName,String template,String source,String jsonInitData) {
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, source);
        mInstance.render(
                packageName,
                template,
                options,
                jsonInitData,
                WXViewUtils.getScreenWidth(this),
                WXViewUtils.getScreenHeight(this),
                WXRenderStrategy.APPEND_ASYNC);
    }


}
