package com.knd.common.activity;

import android.app.Service;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.knd.base.activity.MyBaseActivity;
import com.knd.base.event.MessageEvent;
import com.knd.common.manager.RouteManager;

public abstract class BaseActivity<V extends ViewDataBinding> extends MyBaseActivity<V>{

    public boolean needShow = true;
    private AudioManager am;
    public int statubarHigh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        am = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
    }

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        super.onMessageEvent(messageEvent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(am == null){
            return super.onKeyDown(keyCode, event);
        }
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void statusbarShow(boolean needShow){
        this.needShow = needShow;
    }
}
