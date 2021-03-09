package com.penkin.weatherapp20.view.settings;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface SettingsView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    public void setCelsius();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    public void setFahrenheit();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    public void setWinter();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    public void setSpring();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    public void setNotification();
}
