package com.hyb.rebound;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 类描述：配合{@link ReboundLayout}使用
 * 创建人：huangyaobin
 * 创建时间：2019-08-01
 * 修改人：
 * 修改时间：
 * 修改备注：
 * 使用说明：
 * <com.hyb.rebound.MyScrollView>
 *     <com.hyb.rebound.MyDragLinearLayout>
 *     </com.hyb.rebound.MyDragLinearLayout>
 * </com.hyb.rebound.MyScrollView>
 */
public class MyScrollView extends ScrollView {
    float mLastY;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float curY = ev.getY();
                int deltaY = (int) (curY - mLastY);
                mLastY = curY;

                if(deltaY < 0) { //手指往上划动
                    if(isScrollToBottom()) {
                        return false;
                    }

                }else if(deltaY > 0){ //手指往下滑动
                    if(isScrollToTop()){
                        return false;
                    }

                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean isScrollToBottom() {
        return getScrollY() + getHeight() >= computeVerticalScrollRange();
    }

    private boolean isScrollToTop(){
        return getScrollY() == 0 ;
    }


}