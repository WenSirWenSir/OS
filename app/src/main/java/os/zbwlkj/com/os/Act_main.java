package os.zbwlkj.com.os;import android.annotation.SuppressLint;import android.os.Build;import android.os.Handler;import android.os.Message;import android.os.Bundle;import android.util.Log;import android.view.Window;import android.widget.ImageView;import android.widget.Toast;import com.bumptech.glide.Glide;import com.bumptech.glide.load.engine.DiskCacheStrategy;import net.steamcrafted.loadtoast.LoadToast;import java.util.Timer;import java.util.TimerTask;import okhttp3.internal.cache.CacheStrategy;import os.zbwlkj.com.os.Act.Act_showMain;import os.zbwlkj.com.os.Class.act_class;import os.zbwlkj.com.os.Class.action_class;import os.zbwlkj.com.os.Utils.Utils;/** * 程序的初始化界面 判断是否为API2  初始化欢迎界面 */public class Act_main extends act_class {    /**     * HANDLE的ACTION     */    private final int HANDLE_FINISH_ACT = 0;    private final int HANDLE_TOACT = 1;    private ImageView iv_img;    private LoadToast lt;    private String MSG = "Act_main.java[+]";    private Timer ActTimer;/*启动Act线程*/    private Timer FinshTimer;/*结束本Act线程*/    @SuppressLint("HandlerLeak")    private Handler handler = new Handler() {        @Override        public void handleMessage(Message msg) {            switch (msg.what) {                case HANDLE_FINISH_ACT:                    if (FinshTimer != null) {                        FinshTimer.cancel();                    }                    finish();                    break;                case HANDLE_TOACT:                    if (ActTimer != null) {                        ActTimer.cancel();                    }                    StartAct(Act_showMain.class, false);                    break;            }/*            lt.success();*/            super.handleMessage(msg);        }    };    @Override    protected void onCreate(Bundle savedInstanceState) {        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);        setInTransition(action_class.TRANSITION.SLIDE);        setTransparentBar();        setContentView(R.layout.act_main);        iv_img = findViewById(R.id.act_mainImg);        init();       /* lt = new LoadToast(this);        lt.setText("正在提交");        lt.setBackgroundColor(Color.parseColor("#f30d88"));        lt.setTranslationY(500);        lt.show();*/        super.onCreate(savedInstanceState);    }    /**     * 系统初始化  判断是否为5.0以后的版本 之前的不支持     */    private void init() {        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {            //5.O版本及以上            /*展示图片*/            Glide.with(getApplicationContext()).load("http://120.79.63.36/OS_PHOTOS/1234.png")                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(false).into(iv_img);            ActTimer = new Timer();            ActTimer.schedule(new TimerTask() {                @Override                public void run() {                    Message msg = new Message();                    msg.what = HANDLE_TOACT;                    handler.sendMessage(msg);                }            }, 2000);            FinshTimer = new Timer();            FinshTimer.schedule(new TimerTask() {                @Override                public void run() {                    Message msg = new Message();                    msg.what = HANDLE_FINISH_ACT;                    handler.sendMessage(msg);                }            }, 4000);        } else {            Toast.makeText(getApplicationContext(), Utils.getResourceString(getApplicationContext                    (), R.string.APP_NORUN), Toast.LENGTH_LONG).show();            finish();        }        Log.i(MSG, "屏幕高度:" + getHeightPixels());        Log.i(MSG, "屏幕宽度:" + getWidthPixels());    }}