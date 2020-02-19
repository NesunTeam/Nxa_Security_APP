package com.nesun.sec.apps.biz;

public interface IFeedbackBiz {

    public void submitFeedback(String feedMessage,String feedPhone,OnFeedbackListener onFeedbackListener);
}
