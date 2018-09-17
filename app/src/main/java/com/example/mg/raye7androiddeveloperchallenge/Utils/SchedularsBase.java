package com.example.mg.raye7androiddeveloperchallenge.Utils;

import io.reactivex.Scheduler;

public interface SchedularsBase {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
