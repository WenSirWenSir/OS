package os.zbwlkj.com.os.Utils;import android.animation.ValueAnimator;import android.annotation.SuppressLint;import android.content.Context;import android.graphics.Color;import android.graphics.drawable.GradientDrawable;import android.util.Log;import android.widget.TextView;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;public class Utils {    public static String getResourceString(Context mContext, int _Str) {        try {            return mContext.getResources().getString(_Str).trim();        } catch (Exception e) {            Log.e("Utils.java[+]", "获取系统的字符失败");            return "";        }    }    /**     * 执行系统命令     *     * @return     */    public static String runExecCom(String _cmd) {        Process process = null;        BufferedReader bufferedReader = null;        InputStreamReader inputStreamReader = null;        try {            process = Runtime.getRuntime().exec(_cmd);            inputStreamReader = new InputStreamReader(process.getInputStream());            bufferedReader = new BufferedReader(inputStreamReader);            int read;            char[] buffer = new char[4096];            StringBuilder output = new StringBuilder();            while ((read = bufferedReader.read(buffer)) > 0) {                output.append(buffer, 0, read);            }            return output.toString();        } catch (Exception e) {            if (bufferedReader != null) {                try {                    bufferedReader.close();                } catch (IOException e1) {                    e1.printStackTrace();                }            }            if (inputStreamReader != null) {                try {                    inputStreamReader.close();                } catch (IOException e1) {                    e1.printStackTrace();                }            }            return null;        }    }    /**     * 设置一个背景样式     *     * @param width           线条的宽度     * @param StockColor      线条的颜色     * @param backgroundColor 背景的颜色     * @param radius          角度     * @return GradientDrawable     */    public static GradientDrawable CreateDrawable(int width, String StockColor, String            backgroundColor, int radius) {        GradientDrawable gradientDrawable = new GradientDrawable();        gradientDrawable.setColor(Color.parseColor(backgroundColor));//设置背景        gradientDrawable.setStroke(width, Color.parseColor(StockColor));//设置线条的宽度和颜色        gradientDrawable.setCornerRadius(radius);        return gradientDrawable;    }    /**     * 生产一个背景布局     *     * @param _context     * @param width     * @param StockColor     * @param backgroundColor     * @param raidus     * @return     */    public static GradientDrawable CreateDrawable(Context _context, int width, int StockColor,                                                  int backgroundColor, int raidus) {        GradientDrawable gradientDrawable = new GradientDrawable();        gradientDrawable.setColor(Color.parseColor(_context.getResources().getString                (backgroundColor)));        gradientDrawable.setStroke(width, Color.parseColor(_context.getResources().getString                (StockColor)));        gradientDrawable.setCornerRadius(raidus);        return gradientDrawable;    }    /**     * 执行数字增加动画     *     * @param _start     * @param _end     * @param _tv     */    public static void NumberAddAnimator(Float _start, Float _end, final TextView _tv) {        ValueAnimator animator = ValueAnimator.ofFloat(_start, _end);        animator.setDuration(1500);        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {            @SuppressLint("DefaultLocale")            @Override            public void onAnimationUpdate(ValueAnimator animation) {                _tv.setText(String.format("%.2f", Float.valueOf(animation.getAnimatedValue()                        .toString())));            }        });        animator.start();    }}