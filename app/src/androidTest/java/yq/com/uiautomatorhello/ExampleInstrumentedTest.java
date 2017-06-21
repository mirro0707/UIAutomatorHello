package yq.com.uiautomatorhello;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UiDevice device;//UIAutomator所有的操作入口
    private static final String PACKAGE_NAME = "com.netease.newsreader.activity";
    @Before
    public void setUp() throws Exception {
        //实例化UiDevice
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        //启动应用
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        //等待应用启动
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),10000);//等待包名出现？
        device.wait(Until.hasObject(By.res("com.netease.newsreader.activity:id/bg5")),10000);//直到搜索按钮可见


    }

    @Test
    public void name() throws Exception {
        //定位搜索按钮
        BySelector selector = By.res("com.netease.newsreader.activity:id/bg5");
        UiObject2 search = device.findObject(selector);
        search.click();//点击搜索，页面会跳转

        //加一个等待
        device.wait(Until.hasObject(By.res("com.netease.newsreader.activity:id/aik")),10000);
        //构造选择器
        BySelector selector2 = By.res("com.netease.newsreader.activity:id/aik");
        UiObject2 searchET = device.findObject(selector2);
        searchET.setText("helloworld!");

    }

    @After
    public void tearDown() throws Exception {


    }
}
