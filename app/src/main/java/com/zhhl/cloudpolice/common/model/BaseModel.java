package com.zhhl.cloudpolice.common.model;

import android.app.Application;

import com.google.gson.Gson;

//import com.andromeda.origin.common.signal.SignalBus;

public class BaseModel implements ModelCommon {
    protected Application application;
    protected Gson gson;
//    private SignalBus signalBus;

    public BaseModel(Application application, Gson gson/*, SignalBus signalBus*/) {
        this.application = application;
        this.gson = gson;
//        this.signalBus = signalBus;
    }

    @Override
    public void onDestroy() {

    }
}