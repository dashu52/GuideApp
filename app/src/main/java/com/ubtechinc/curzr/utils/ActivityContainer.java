package com.ubtechinc.curzr.utils;

import android.util.Log;

import com.ubtechinc.curzr.guideapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Manage all activities in the application. <br/>
 * Date: 2016/12/18 <br/>
 *
 * @author mr.hoo7793@gmail.com
 */
public class ActivityContainer {

    private static final String TAG = ActivityContainer.class.getSimpleName();
    /**
     * 保存记录的Activity列表
     */
    private List<BaseActivity> mActivityList;

    private ActivityContainer(){
        mActivityList = new ArrayList<>();
    }
    private static class InstanceHolder{
        private final static ActivityContainer INSTANCE = new ActivityContainer();
    }

    public static ActivityContainer getInstance(){
        return InstanceHolder.INSTANCE;
    }

    /**
     * check whether the activity exists in the manager.
     * @param activity
     * @return
     */
    public boolean isExist(BaseActivity activity){

        if(activity == null){
            return false;
        }

        for(int i = 0; i < mActivityList.size(); i ++){
            BaseActivity element = mActivityList.get(i);
            if(element == activity){
                return true;
            }
        }

        return false;

    }

    /**
     * check whether the activity is at the top of manager.
     */
    public boolean isTopActivity(BaseActivity activity){

        if(activity == null || mActivityList.isEmpty()){
            return false;
        }

        int lastIndex = mActivityList.size() - 1;
        BaseActivity top = mActivityList.get(lastIndex);
        return (top == activity);
    }

    /**
     * check whether the activity is at the top of manager.
     */
    public boolean isTopActivity(Class<?> cls){

        if(mActivityList.isEmpty()){
            return false;
        }

        int lastIndex = mActivityList.size() - 1;
        BaseActivity topActivity = mActivityList.get(lastIndex);
        return topActivity.getClass().equals(cls);
    }

    /**
     * add the specified activity to the manager.
     * @param activity the specified activity
     */
    public void add(BaseActivity activity) {

        if(activity == null){
            Log.e(TAG,"activity is null");
            return;
        }

        if(!isExist(activity)){
            mActivityList.add(activity);
        }

        else {
            Log.e(TAG,"already added the same activity " + activity);
        }
    }

    /**
     * get the activity with the specified class without removing it.
     * @param cls the specified class.
     * @return the activity with the specified class.
     */
    public BaseActivity getActivity(Class<?> cls) {

        int size = mActivityList.size();
        for(int i = 0; i < size; i++) {
            BaseActivity activity = mActivityList.get(i);
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

//    /**
//     * start activity with the specified class which is held by the manager.
//     * @param cls the specified class.
//     * @param resume whether run resume() method.
//     */
//    public void goToActivity(Class<?> cls, boolean resume){
//
//        BaseActivity target = getActivity(cls);
//        if(target == null){
//            Log.d(TAG,cls.getSimpleName() + " instance not found in the manager.");
//            return;
//        }
//
//        int size = mActivityList.size();
//        for(int i = size - 1; i >= 0; i--){
//            BaseActivity activity = mActivityList.get(i);
//            if(activity == target){
//                if(resume){
//                    activity.prepareResume();
//                }
//                return;
//            }
//            activity.finish(); //onDestroy of BaseActivity will remove it.
//        }
//    }

//    /**
//     * start activity with the specified class which is held by the manager.
//     * @param cls the specified class.
//     */
//    public void goToActivity(Class<?> cls){
//        goToActivity(cls,false);
//    }

    /**
     * return true if the manager contains no activities.
     * @return true or false
     */
    public boolean isEmpty() {
        return mActivityList.isEmpty();
    }

    /**
     * return the number of activities in the manager.
     * @return size
     */
    public int size() {
        return mActivityList.size();
    }


    /**
     * Removes the specified activity
     */
    public void remove(BaseActivity activity) {

        if (activity == null){
            Log.e(TAG,"activity is null");
            return;
        }

        int size = mActivityList.size();
        for(int i = 0; i < size; i++) {
            BaseActivity element = mActivityList.get(i);
            if (element == activity) {
                mActivityList.remove(i);
                Log.d(TAG,"remove activity:" + activity.getClass().getSimpleName() + " index = " + i);
                return;
            }
        }

    }

    /**
     * finish the specified activity
     * @param activity the specified activity.
     */
    public void finish(BaseActivity activity){

        if(activity == null){
            return;
        }

        if(!activity.isFinishing()){
            activity.finish();
        }

    }

    /**
     * finish the activity with the specified class without removing it.
     * @param cls the specified class.
     * @return whether the activity is finished.
     */
    public boolean finish(Class<?> cls) {

        int size = mActivityList.size();
        for(int i = 0; i < size; i++) {
            BaseActivity activity = mActivityList.get(i);
            if (activity.getClass().equals(cls)) {
                 finish(activity);
                 return true;
            }
        }
        return false;
    }

    /**
     * finish all activities contained in the manager.
     */
    public void clear() {

        int size = mActivityList.size();
        for (int i = size - 1; i >= 0; i--) {
            BaseActivity activity = mActivityList.get(i);
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        //System.exit(0);
    }
}
