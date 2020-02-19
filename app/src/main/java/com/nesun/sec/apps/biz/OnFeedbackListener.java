package com.nesun.sec.apps.biz;

import com.nesun.sec.apps.bean.FeedbackBean;

public interface OnFeedbackListener {

    void feedbackSuccess(FeedbackBean feedbackBean);

    void feedbackFailed();

    void feedbackError();

}
