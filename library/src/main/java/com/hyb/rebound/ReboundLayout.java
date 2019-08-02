package com.hyb.rebound;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 类描述：拥有越界回弹效果的FrameLayout
 * 创建人：huangyaobin
 * 创建时间：2019-08-01
 * 修改人：
 * 修改时间：
 * 修改备注：
 * 使用说明：如果普通界面则直接包住就行，
 * <com.hyb.rebound.ReboundLayout>
 *     你的布局
 * </com.hyb.rebound.ReboundLayout>
 *
 *  若有使用scrollview，则
 * <com.hyb.rebound.MyScrollView>
 *     <com.hyb.rebound.ReboundLayout>
 *     </com.hyb.rebound.ReboundLayout>
 * </com.hyb.rebound.MyScrollView>
 */
public class ReboundLayout extends FrameLayout {


    private ViewDragHelper mViewDragHelper;
    int damping = 3;//阻尼

    public ReboundLayout(Context context) {
        this(context, null);
    }

    public ReboundLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReboundLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewDrag);
        if(array.getInteger(R.styleable.ViewDrag_damping , 0) != 0){
            damping = array.getInteger(R.styleable.ViewDrag_damping , 0);
        }

        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            private int mLeft = -1;
            private int mTop = -1;

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
                if(mLeft == -1){
                    mLeft = capturedChild.getLeft();
                }

                if(mTop == -1){
                    mTop = capturedChild.getTop();
                }


            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {

                return child.getTop() + dy / damping;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //只允许上下拖动
                return mLeft;

            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                mViewDragHelper.settleCapturedViewAt(mLeft, mTop);
                invalidate();
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    public void setDamping(int damping){
        this.damping = damping;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper != null && mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}