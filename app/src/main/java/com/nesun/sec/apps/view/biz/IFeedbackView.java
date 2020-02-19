package com.nesun.sec.apps.view.biz;

public interface IFeedbackView {

    String getFeedbackMessage();
    String getFeedbackPhone();

    void showLoding();
    void hindLoding();

    void showFeedbackSuccess();
    void showFeedbackFaild();
    void showFeedbackError();


}
