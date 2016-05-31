package com.miapp.Activity;

import java.util.Observable;

public class MyObserverable extends Observable {

    public static MyObserverable observerable;

    // 单例:在所有内存中，只存在一个此对象
    public static MyObserverable getObserverable() {
        if (observerable == null) {
            observerable = new MyObserverable();
        }
        return observerable;
    }

    public void setMessage(Object msg) {
        // 告诉观察者对象，所观察的内容发生改变
        observerable.setChanged();
        // 刷新信息，或者信息传递
        observerable.notifyObservers(msg);
    }

}
