package com.innotek.demotestproject.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.innotek.demotestproject.R;

/**
 * Created by admin on 2017/7/18.
 */

public class SearchEditView extends LinearLayout {

    private Drawable drawable_delete;
    private Drawable bg_shape_gray;
    private String inputStr;
    private boolean isCompleted;
    private Context context;

    public SearchEditView(Context context) {
        super(context);
        init(context);
    }

    public SearchEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SearchEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SearchEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void setText(String inputStr) {
        this.inputStr = inputStr;
    }

    public String getText() {
        return inputStr;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
        //invalidate();
        if(isCompleted){
            if(getChildCount()>0){
                removeAllViews();
            }
            addView(EditBg(context));
        }

    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void init(Context context) {
        this.context = context;
        drawable_delete = context.getResources().getDrawable(R.mipmap.icon_delete);
        bg_shape_gray = context.getResources().getDrawable(R.drawable.bg_shape_gray);
    }


   public View EditBg(Context context){
       LinearLayout linearLayout = new LinearLayout(context);
       //父LinearLayout LayoutParams
       LinearLayout.LayoutParams parentparams = new LinearLayout
                .LayoutParams(LayoutParams.WRAP_CONTENT
               , LinearLayout.LayoutParams.MATCH_PARENT);
       parentparams.setMargins(5,5,5,5);
       linearLayout.setLayoutParams(parentparams);
       linearLayout.setOrientation(HORIZONTAL);
       Button btn = new Button(context);
       btn.setText(getText());
       btn.setBackgroundDrawable(null);
       linearLayout.addView(btn);
       ImageView imageView = new ImageView(context);
       imageView.setImageResource(R.mipmap.icon_delete);
       linearLayout.addView(imageView);
       linearLayout.setBackgroundDrawable(bg_shape_gray);
       return linearLayout;
   }
}
