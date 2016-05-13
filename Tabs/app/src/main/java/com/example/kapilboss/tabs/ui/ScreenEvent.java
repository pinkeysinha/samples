package com.example.kapilboss.tabs.ui;

/**
 * Created by kapilsharma on 10/05/16.
 */
public class ScreenEvent<T> {
    public int screen;
    public T data;

    public ScreenEvent(int screen, T o) {
        this.screen = screen;
        data = o;
    }
}
