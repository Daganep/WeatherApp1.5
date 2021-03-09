package com.penkin.weatherapp20.view.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.penkin.weatherapp20.R;
import com.penkin.weatherapp20.databinding.FragmentSettingsBinding;
import com.penkin.weatherapp20.presenter.SettingsPresenter;

import java.util.Objects;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class SettingsFragment extends MvpAppCompatFragment implements SettingsView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @InjectPresenter
    SettingsPresenter presenter;
    private FragmentSettingsBinding settingsBinding;
    private int activeButtonColor = R.color.design_default_color_error;
    private int passiveButtonColor = R.color.colorGreenPrimary;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false);
        return settingsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        initToolbar();
        clickListenerInit();
        presenter.setButtons();
    }

    private void initToolbar(){
        ((AppCompatActivity) requireActivity()).setSupportActionBar(settingsBinding.settingsToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void clickListenerInit(){
        settingsBinding.celButtonSetFrag.setOnClickListener(this);
        settingsBinding.farButtonSetFrag.setOnClickListener(this);
        settingsBinding.winterThemeSetFrag.setOnClickListener(this);
        settingsBinding.springThemeSetFrag.setOnClickListener(this);
        settingsBinding.notSwitchSetFrag.setOnCheckedChangeListener(this);
    }

    //Log.d will be deleted, there will be button action
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.celButtonSetFrag:{
                settingsBinding.celButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(activeButtonColor, null));
                settingsBinding.farButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(passiveButtonColor, null));
                presenter.setUnits(true);
                break;
            }
            case R.id.farButtonSetFrag:{
                settingsBinding.celButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(passiveButtonColor, null));
                settingsBinding.farButtonSetFrag.setBackgroundColor(getResources()
                        .getColor(activeButtonColor, null));
                presenter.setUnits(false);
                break;
            }
            case R.id.winterThemeSetFrag:{
                settingsBinding.winterThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(activeButtonColor, null));
                settingsBinding.springThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(passiveButtonColor, null));
                presenter.setTheme(getString(R.string.winter));
                //if(getActivity() != null)getActivity().recreate();
                break;
            }
            case R.id.springThemeSetFrag:{
                settingsBinding.winterThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(passiveButtonColor, null));
                settingsBinding.springThemeSetFrag.setBackgroundColor(getResources()
                        .getColor(activeButtonColor, null));
                presenter.setTheme(getString(R.string.spring));
                //if(getActivity() != null)getActivity().recreate();
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        presenter.setNotification(isChecked);
    }

    @Override
    public void setCelsius(){
        settingsBinding.celButtonSetFrag
                .setBackgroundColor(getResources()
                .getColor(R.color.design_default_color_error, null));
    }

    @Override
    public void setFahrenheit(){
        settingsBinding.farButtonSetFrag
                .setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
    }

    @Override
    public void setWinter(){
        settingsBinding.winterThemeSetFrag
                .setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
    }

    @Override
    public void setSpring(){
        settingsBinding.springThemeSetFrag
                .setBackgroundColor(getResources()
                        .getColor(R.color.design_default_color_error, null));
    }

    @Override
    public void setNotification(){
        settingsBinding.notSwitchSetFrag.setChecked(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        settingsBinding = null;
    }
}
