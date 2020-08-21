package com.innotek.demotestproject.view.indicatorview;

/**
 * Created by admin on 2017/12/25.
 * 指示器bean
 */

public class IndicatorBean {

    /**
     * 指示器颜色
     */
    public int circleIndicatorColor;
    /**
     * 线的状态 实线虚线
     */
    public boolean isDashLine;
    /**
     *  是否有线
     */
    public boolean hasLine = true;
    /**
     * 线的颜色
     */
    public int lineColor;
    /**
     * 指示器文字
     */
    public String indicatorText;
    /**
     * 指示器文字颜色
     */
    public int indicatorTexttColor;

    /**
     * 指示器上部文字
     */
    public String indicatorAboveText;
    /**
     * 指示器上部文字颜色
     */
    public int indicatroAboveTextColor;
    /**
     * 是否显示上部文字
     */
    public boolean isShowAboveText;


    /**
     * 指示器下部文字
     */
    public String indicatorButtomText;
    /**
     * 指示器下部文字颜色
     */
    public int indicatroButtomTextColor;
    /**
     * 是否显示下部文字
     */
    public boolean isShowButtomText;
    /**
     *  下部文字是否需要换行
     */
    public boolean buttomTextIsNeedNewLine;

    /**
     * 小车index
     */
    public int carIndex ;
}
