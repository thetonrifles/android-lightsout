package com.appenjoyer.lightsout.Interfaces;

import com.appenjoyer.lightsout.views.ItemView;

public interface OnToggledListener {
    //Item from GridView clicked
    void OnToggled(ItemView v, boolean touchOn);
}