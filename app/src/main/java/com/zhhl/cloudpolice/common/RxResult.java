package com.zhhl.cloudpolice.common;

public interface RxResult {
    void onError(Throwable throwable);

    void onComplete();
}
