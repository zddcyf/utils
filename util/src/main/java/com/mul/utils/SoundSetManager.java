package com.mul.utils;

import android.content.Context;
import android.media.AudioManager;

import com.mul.utils.manager.GlobalManager;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils
 * @ClassName: SoundSetManager
 * @Author: zdd
 * @CreateDate: 2021/3/4 10:18:18
 * @Description: 系统声音控制类
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/3/4 10:18:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SoundSetManager {
    private final AudioManager audioManager;

    private SoundSetManager() {
        audioManager = (AudioManager) GlobalManager.INSTANCE.context.getSystemService(Context.AUDIO_SERVICE);
    }

    private static class SoundSetSingleton {
        private static final SoundSetManager SOUND_SET_MANAGER = new SoundSetManager();
    }

    public static SoundSetManager get() {
        return SoundSetSingleton.SOUND_SET_MANAGER;
    }

    /**
     * 设置铃声声音模式
     *
     * @param mode 声音模式 AudioManager.RINGER_MODE_NORMAL
     *             静音模式 AudioManager.RINGER_MODE_SILENT
     *             震动模式 AudioManager.RINGER_MODE_VIBRATE
     */
    public void setRingerMode(int mode) {
        audioManager.setRingerMode(mode);
    }

    /**
     * 获取铃声震动模式
     */
    public void getRingerMode() {
        audioManager.getRingerMode();
    }

    public void setVolume() {
        //最大音量
//        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //当前音量
//        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 直接控制音量
//        if (isSilent) {
//            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
//        } else {
//            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, tempVolume, 0); //tempVolume:音量绝对值
//        }
    }

    /**
     * 设置音频模式
     *
     * @param mode           AudioManager.MODE_NORMAL 正常模式，使用完毕后需要切换回来
     *                       AudioManager.MODE_IN_CALL 通话音量
     * @param speakerphoneOn mode为-1时，false为蓝牙
     *                       mode为AudioManager.MODE_IN_CALL时，true为免提 false为听筒
     */
    public void setMode(int mode, boolean speakerphoneOn) {
        if (mode == AudioManager.MODE_NORMAL) {
            audioManager.setMode(mode);
            audioManager.setSpeakerphoneOn(speakerphoneOn);
        } else if (mode == AudioManager.MODE_IN_CALL) {
            audioManager.setMode(mode);
            audioManager.setSpeakerphoneOn(speakerphoneOn);
        } else {
            audioManager.setSpeakerphoneOn(speakerphoneOn);
        }
    }

    /**
     * 获取音频模式
     */
    public void getMode() {
        audioManager.getMode();
    }
}
