package os.zbwlkj.com.os.Act;import android.annotation.SuppressLint;import android.content.Context;import android.os.Bundle;import android.util.Log;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.widget.ImageView;import android.widget.RelativeLayout;import com.bigkoo.convenientbanner.ConvenientBanner;import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;import com.bigkoo.convenientbanner.holder.Holder;import com.bumptech.glide.Glide;import com.bumptech.glide.load.engine.DiskCacheStrategy;import java.util.ArrayList;import os.zbwlkj.com.os.Act.Shop.toPayshop;import os.zbwlkj.com.os.Act.Shop.toSeeShopdetails;import os.zbwlkj.com.os.Act.System.toSeebusiness;import os.zbwlkj.com.os.Class.action_class;import os.zbwlkj.com.os.Class.fragment_class;import os.zbwlkj.com.os.R;import os.zbwlkj.com.os.Utils.TextUnt;import os.zbwlkj.com.os.Utils.Utils;/** * 展示商品/票跟/干货/生鲜/水果/饮料/牛奶/零食的界面 * 用来驱动Fragment的管理器 */public class Act_showMain extends fragment_class {    private String MSG = "Act_showMain.java[+]";    private String action = "0";    private final String ACTION_TO_SHOP = "0";    @Override    protected void onCreate(Bundle savedInstanceState) {        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);        setTransparentBar();        setInTransition(action_class.TRANSITION.SLIDE);/*设置进入动画 */        setOutTransition(action_class.TRANSITION.EXPLODE);/*设置退出动画*/        String i = Utils.getCountDown("1578248888", "hh:mm:ss");        Log.i(MSG, "时间戳返回的日期为:" + i);        init();        super.onCreate(savedInstanceState);    }    /**     * 界面初始化     */    private void init() {        switch (action) {            case ACTION_TO_SHOP:                Handle_to_shop();                break;        }    }    /**     * 如果回传的表示是商品 就处理商品     */    @SuppressLint("ResourceType")    private void Handle_to_shop() {        setTransparentBar();        setContentView(R.layout.act_shop);        ConvenientBanner banner;        banner = findViewById(R.id.act_shopBanner);/*设置轮播*/        /*设置按钮的边框*/        findViewById(R.id.act_shopBtnBody).setBackground(Utils.CreateDrawable(1, "#ffffff",                "#ffffff", 10));        /*设置支付按钮的边框*/        findViewById(R.id.act_shopBtnPayBody).setBackground(Utils.CreateDrawable(1, "#ffffff",                "#ffffff", 300));        /*设置左边的导航*/        findViewById(R.id.act_shopleftBtnbody).setBackground(Utils.CreateDrawable(1, "#ffffff",                "#ffffff", 10));        /*设置倒计时*/        findViewById(R.id.act_shopdownTimeBody).setBackground(Utils.CreateDrawable(1, "#ffffff",                "#ffffff", 10));        /*设置右边的导航*/        findViewById(R.id.act_shopRightBtnbody).setBackground(Utils.CreateDrawable(1, "#ffffff",                "#ffffff", 10));        /*设置点赞的字体*/        TextUnt.with(this, R.id.act_shopLovenumber).setFontFile(getApplicationContext(),                "number_2");        ArrayList<String> img_url = new ArrayList();        img_url.add("http://120.79.63.36/OS_PHOTOS/123.png");        img_url.add("http://120.79.63.36/OS_PHOTOS/1234.png");        img_url.add("http://120.79.63.36/OS_PHOTOS/123.png");        img_url.add("http://120.79.63.36/OS_PHOTOS/1234.png");        /**         * 开启循环轮播         */        banner.setPages(new CBViewHolderCreator() {            @Override            public PagesHoler createHolder() {                return new PagesHoler();            }        }, img_url).startTurning(5000).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign                .ALIGN_PARENT_LEFT);        /**         * 监听事件         */        findViewById(R.id.act_shopBtnPayBody).setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                StartAct(toPayshop.class, false);            }        });        /**         * 查看商家的监听事件         */        findViewById(R.id.act_shopBtnbusiness).setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                StartAct(toSeebusiness.class, false);            }        });        /**         * 查看商品的详情         */        findViewById(R.id.act_shopBtnshopMsg).setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                StartAct(toSeeShopdetails.class, false);            }        });/*        findViewById(R.id.act_shopBtnPay).setBackground(Utils.CreateDrawable(1, getResources()                .getString(R.color.SystemTheme), getResources().getString(R.color.SystemTheme),                300));*/    }    /**     * 类的实现方法     */    class PagesHoler implements Holder<String> {        private ImageView img;        @Override        public View createView(Context context) {            img = new ImageView(context);            img.setAdjustViewBounds(true);            img.setScaleType(ImageView.ScaleType.FIT_XY);            return img;        }        @Override        public void UpdateUI(Context context, int position, String data) {            Glide.with(context).load(data).diskCacheStrategy(DiskCacheStrategy.NONE)                    .skipMemoryCache(false).into(img);        }    }}