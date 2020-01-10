package os.zbwlkj.com.os.Utils;import android.animation.ValueAnimator;import android.annotation.SuppressLint;import android.content.Context;import android.content.SharedPreferences;import android.content.pm.PackageInfo;import android.content.pm.PackageManager;import android.graphics.Color;import android.graphics.drawable.GradientDrawable;import android.support.graphics.drawable.VectorDrawableCompat;import android.util.Log;import android.view.View;import android.view.inputmethod.InputMethodManager;import android.widget.TextView;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;import java.security.MessageDigest;import java.security.NoSuchAlgorithmException;import java.text.SimpleDateFormat;import java.util.Date;import java.util.Locale;public class Utils {    private static String MSG = "Utils.java[+]";    public static String getResourceString(Context mContext, int _Str) {        try {            return mContext.getResources().getString(_Str).trim();        } catch (Exception e) {            Log.e("Utils.java[+]", "获取系统的字符失败");            return "";        }    }    /**     * 执行系统命令     *     * @return     */    public static String runExecCom(String _cmd) {        Process process = null;        BufferedReader bufferedReader = null;        InputStreamReader inputStreamReader = null;        try {            process = Runtime.getRuntime().exec(_cmd);            inputStreamReader = new InputStreamReader(process.getInputStream());            bufferedReader = new BufferedReader(inputStreamReader);            int read;            char[] buffer = new char[4096];            StringBuilder output = new StringBuilder();            while ((read = bufferedReader.read(buffer)) > 0) {                output.append(buffer, 0, read);            }            return output.toString();        } catch (Exception e) {            if (bufferedReader != null) {                try {                    bufferedReader.close();                } catch (IOException e1) {                    e1.printStackTrace();                }            }            if (inputStreamReader != null) {                try {                    inputStreamReader.close();                } catch (IOException e1) {                    e1.printStackTrace();                }            }            return null;        }    }    /**     * 设置一个背景样式     *     * @param width           线条的宽度     * @param StockColor      线条的颜色     * @param backgroundColor 背景的颜色     * @param radius          角度     * @return GradientDrawable     */    public static GradientDrawable CreateDrawable(int width, String StockColor, String            backgroundColor, int radius) {        GradientDrawable gradientDrawable = new GradientDrawable();        gradientDrawable.setColor(Color.parseColor(backgroundColor));//设置背景        gradientDrawable.setStroke(width, Color.parseColor(StockColor));//设置线条的宽度和颜色        gradientDrawable.setCornerRadius(radius);        return gradientDrawable;    }    /**     * 生产一个背景布局     *     * @param _context     * @param width     * @param StockColor     * @param backgroundColor     * @param raidus     * @return     */    public static GradientDrawable CreateDrawable(Context _context, int width, int StockColor,                                                  int backgroundColor, int raidus) {        GradientDrawable gradientDrawable = new GradientDrawable();        gradientDrawable.setColor(Color.parseColor(_context.getResources().getString                (backgroundColor)));        gradientDrawable.setStroke(width, Color.parseColor(_context.getResources().getString                (StockColor)));        gradientDrawable.setCornerRadius(raidus);        return gradientDrawable;    }    /**     * 执行数字增加动画     *     * @param _start     * @param _end     * @param _tv     */    public static void NumberAddAnimator(Float _start, Float _end, final TextView _tv) {        ValueAnimator animator = ValueAnimator.ofFloat(_start, _end);        animator.setDuration(1500);        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {            @SuppressLint("DefaultLocale")            @Override            public void onAnimationUpdate(ValueAnimator animation) {                _tv.setText(String.format("%.2f", Float.valueOf(animation.getAnimatedValue()                        .toString())));            }        });        animator.start();    }    /**     * 隐藏软键盘     *     * @param view     */    public static void hideKeyboard(View view) {        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context                .INPUT_METHOD_SERVICE);/*获取服务*/        if (imm != null) {            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);        }    }    /**     * 使得某一控件获取焦点     *     * @param view     */    public static void getFocusable(View view) {        view.setFocusable(true);        view.setFocusableInTouchMode(true);        view.requestFocus();    }    /**     * saved user data     *     * @param tContext     * @param tdata    Characters of name,key     */    @SuppressLint({"ApplySharedPref", "LongLogTag"})    public static void saveToken(Context tContext, String... tdata) {        SharedPreferences sharedPreferences = tContext.getSharedPreferences("OS_USERPAGE", 0);        try {            sharedPreferences.edit().putString(tdata[0], tdata[1]).commit();        } catch (Exception e) {        }    }    /**     * get the Token data saved by the User     *     * @param key Want key     * @return     */    @SuppressLint("LongLogTag")    public static String getToKen(Context tContext, String key) {        SharedPreferences sharedPreferences = tContext.getSharedPreferences("OS_USERPAGE", 0);        try {            return sharedPreferences.getString(key, "");//如果不存在  就返回一个空字符        } catch (Exception e) {            return "";        }    }    /**     * 设置一个SVG的颜色     *     * @param _context     * @param _id      设置的图片ID     * @param _color   设置颜色     */    public static VectorDrawableCompat getSvgDrawable(Context _context, int _id, String _color) {        VectorDrawableCompat data = VectorDrawableCompat.create(_context.getResources(), _id,                _context.getTheme());        data.setTint(Color.parseColor(_color.trim()));        return data;    }    /**     * 时间戳转换成时间     *     * @param _stamp 到期时间     * @param _type  mm:ss     * @return     */    public static String getCountDown(String _stamp, String _type) {        /**         * 获取系统的现在时间         */        int nowStmap = Integer.parseInt(getTimeMillis());        int downTime = Integer.parseInt(_stamp) - nowStmap;        Log.i(MSG, "剩余时间为:" + downTime);        Log.i(MSG, "获取系统的时间戳为:" + nowStmap);        if (downTime >= 0) {            /*还没有过期 计算剩余时间*/            _stamp = String.valueOf((nowStmap + downTime) - 1);            Log.i(MSG, "Stmap的时间为:" + _stamp);        } else {            Log.i(MSG, "时间到期了");        }/*        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm:ss秒");*/        SimpleDateFormat sdr = new SimpleDateFormat(_type);        @SuppressWarnings("unused") long lcc = Long.valueOf(_stamp);        int i = Integer.parseInt(_stamp);        String times = sdr.format(new Date(i * 1000L));        return times;    }    /**     * 获取一个精确到秒的时间戳     *     * @return     */    public static String getTimeMillis() {        return String.valueOf(System.currentTimeMillis() / 1000);    }    /**     * 获取程序的哈希值     * @param context     * @return     */    public static String getSha1(Context context) {        try {            PackageInfo info = context.getPackageManager().getPackageInfo(                    context.getPackageName(), PackageManager.GET_SIGNATURES);            byte[] cert = info.signatures[0].toByteArray();            MessageDigest md = MessageDigest.getInstance("SHA1");            byte[] publicKey = md.digest(cert);            StringBuffer hexString = new StringBuffer();            for (int i = 0; i < publicKey.length; i++) {                String appendString = Integer.toHexString(0xFF & publicKey[i])                        .toUpperCase(Locale.US);                if (appendString.length() == 1)                    hexString.append("0");                hexString.append(appendString);                hexString.append(":");            }            String result = hexString.toString();            return result.substring(0, result.length()-1);        } catch (PackageManager.NameNotFoundException e) {            e.printStackTrace();        } catch (NoSuchAlgorithmException e) {            e.printStackTrace();        }        return null;    }    /**     * 转换工具     * @param context     * @param dp     * @return     */    public static int dpTopx(Context context, float dp) {        final float scale = context.getResources().getDisplayMetrics().density;        return (int) (dp * scale + 0.5f);    }    public static int pxTodp(Context context, float px) {        final float scale = context.getResources().getDisplayMetrics().density;        return (int) (px / scale + 0.5f);    }    public static int pxTosp(Context context, float px) {        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;        return (int) (px / fontScale + 0.5f);    }    public static int spTopx(Context context, float sp) {        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;        return (int) (sp * fontScale + 0.5f);    }}