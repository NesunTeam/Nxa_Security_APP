package com.nesun.sec.apps.presenter;

import android.os.Handler;

import com.nesun.sec.apps.bean.FeedbackBean;
import com.nesun.sec.apps.biz.FeedbackBiz;
import com.nesun.sec.apps.biz.IFeedbackBiz;
import com.nesun.sec.apps.biz.OnFeedbackListener;
import com.nesun.sec.apps.view.biz.IFeedbackView;

public class FeedbackPresenter {

    private IFeedbackBiz iFeedbackBiz;
    private IFeedbackView iFeedbackView;
    private Handler mHandler = new Handler();


    public  FeedbackPresenter(IFeedbackView view){
        this.iFeedbackView =view;
        iFeedbackBiz = new FeedbackBiz();
    }

    /**
     * 提交反馈
     */
    public void submit(){
        iFeedbackView.showLoding();
        iFeedbackBiz.submitFeedback(iFeedbackView.getFeedbackMessage(), iFeedbackView.getFeedbackPhone(), new OnFeedbackListener() {
            @Override
            public void feedbackSuccess(FeedbackBean feedbackBean) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFeedbackView.hindLoding();
                        iFeedbackView.showFeedbackSuccess();
                    }
                });
            }
            @Override
            public void feedbackFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFeedbackView.hindLoding();
                        iFeedbackView.showFeedbackFaild();
                    }
                });
            }
            @Override
            public void feedbackError() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iFeedbackView.hindLoding();
                        iFeedbackView.showFeedbackError();
                    }
                });
            }
        });

    }





}
