package com.example.mg.raye7androiddeveloperchallenge.Utils;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedularsUtils implements SchedularsBase {
    private static SchedularsUtils INSTANCE;

    @Inject
    public SchedularsUtils() {
    }

    public static synchronized SchedularsUtils getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new SchedularsUtils();
        return INSTANCE;
    }

    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    public Scheduler io() {
        return Schedulers.io();
    }

    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

}
