package yq.com.uiautomatorhello;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 缺点：webview只能坐标 优点：不需要源码 （robotuim缺点跨应用）
 *
 * 1）UIAutomator2右键可以直接运行（不再像uiautomator1需要ant命令构建、push到手机特定目录再运行命令执行）
 * 2）所有对象都叫 UiObject2
 * 3）查找控件：device.findObject(BySelector)
 * 4）控件操作：控件.操作
 *
 * 测试对象：模拟器里的计算器
 */

@RunWith(AndroidJUnit4.class)
public class HelloWorld {
    private UiDevice device;//UIAutomator所有的操作入口
    private static final String PACKAGE_NAME = "com.android.calculator2";//应用的包名

    @Before
    public void setUp() throws Exception {

        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());//实例化UiDevice
        //获取上下文
        Context context = InstrumentationRegistry.getContext();
        //将包名传给包管理器获取启动的intent
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//之前如果打开了这个应用会把它清除
        //启动应用
        context.startActivity(intent);
        //等待应用启动
        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),10000);//等待包名出现

    }

    @Test
    public void cal() throws Exception {
        device.findObject(By.res("com.android.calculator2:id/digit_1")).click();//点击1
        device.findObject(By.res("com.android.calculator2:id/op_add")).click();//点击+
        device.findObject(By.res("com.android.calculator2:id/digit_2")).click();//点击2
        device.findObject(By.res("com.android.calculator2:id/eq")).click();//点击=
        String result = device.findObject(By.res("com.android.calculator2:id/result")).getText();//计算结果
        System.out.println(result);

    }

    @After
    public void tearDown() throws Exception {


    }
}
