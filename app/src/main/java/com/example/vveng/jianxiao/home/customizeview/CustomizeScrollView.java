package com.example.vveng.jianxiao.home.customizeview;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ${justin} on 2017/10/2515: 55
 * WeChat:Justin-Tz
 * 重写滚动视图的方法，用于监听滚动，用于得到滚动距离
 * 这里使用了 NestedScrollView 可以防止下拉刷新与轮播图冲突9
 */

public class CustomizeScrollView extends NestedScrollView {

    private int downX;
    private int downY;
    private int mTouchSlop;

    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;

    private OnScrollistener onScrollistener;

    public void setOnScrollistener(OnScrollistener onScrollistener) {
        this.onScrollistener = onScrollistener;
    }
    public interface OnScrollistener {
        void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

    public CustomizeScrollView(Context context) {
        super(context);
    }

    public CustomizeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     * onScrollChanged里面有4个参数，l代表滑动后当前ScrollView可视界面的左上角在整个ScrollView的X轴中的位置，
     * oldi也就是滑动前的X轴位置了。同理，t也是当前可视界面的左上角在整个ScrollView的Y轴上的位置，
     * oldt也就是移动前的Y轴位置了。
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                downX = (int) e.getRawX();
//                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
//                int moveY = (int) e.getRawY();
//                if (Math.abs(moveY - downY) > mTouchSlop) {
//                    return true;
//                }

        }
        return super.onInterceptTouchEvent(e);
    }





    //    //防止viewpager卡顿
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            //获取按下的坐标
//            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                xLast = ev.getX();
//                yLast = ev.getY();
//                break;
//            //计算移动的xy距离
//            case MotionEvent.ACTION_MOVE:
//                final float curX = ev.getX();
//                final float curY = ev.getY();
//
//                xDistance += Math.abs(curX - xLast);
//                yDistance += Math.abs(curY - yLast);
//                xLast = curX;
//                yLast = curY;
//
//                //如果x滑动的距离大于y方向就判断是横向滑动，返回false就不拦截触摸事件
////                if(xDistance > yDistance){
////                    return false;
////                }else
//            if (Math.abs(yDistance - curY) > mTouchSlop) {//滑动的距离减去刚开始的距离大于0就是上下滑动，拦截事件
//                return false;
//        }
//
//        }
//
//        return super.onInterceptTouchEvent(ev);
//    }


}
