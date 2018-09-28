package com.example.omerom88.application;

import com.example.omerom88.screens.BasicFragment;

/**
 * Created by omerom88 on 13-Sep-18
 */
public class AddFragmentEvent {
    private BasicFragment fragment;
    private boolean isReplace;
    private boolean addToBackStack;

    public AddFragmentEvent(BasicFragment fragment, boolean isReplace, boolean addToBackStack) {
        this.fragment = fragment;
        this.isReplace = isReplace;
        this.addToBackStack = addToBackStack;
    }

    public BasicFragment getFragment() {
        return fragment;
    }

    public boolean isReplace() {
        return isReplace;
    }

    public void setReplace(boolean replace) {
        isReplace = replace;
    }

    public boolean isAddToBackStack() {
        return addToBackStack;
    }
}
