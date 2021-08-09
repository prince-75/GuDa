package com.example.guda.Network;

import android.os.Environment;

import java.io.File;

public class Constants {
    public static final int HTTP_PORT = 12345;
    public static final String DIR_IN_SDCARD = "Guda";
    public static final String Video_DIR_IN_SDCARD = "Guda/video";
    public static final String Data_DIR_IN_SDCARD = "Guda/data";
    public static final int MSG_DIALOG_DISMISS = 0;
    public static final File DIR = new File(Environment.getExternalStorageDirectory() + File
            .separator + Constants.DIR_IN_SDCARD);
    public static final File VideoDIR = new File(Environment.getExternalStorageDirectory() +
            File.separator + Constants.Video_DIR_IN_SDCARD);
    public static final File DataDIR = new File(Environment.getExternalStorageDirectory() +
            File.separator + Constants.Data_DIR_IN_SDCARD);

    public static final class RxBusEventType {
        public static final String POPUP_MENU_DIALOG_SHOW_DISMISS = "POPUP MENU DIALOG SHOW " +
                "DISMISS";
        public static final String WIFI_CONNECT_CHANGE_EVENT = "WIFI CONNECT CHANGE EVENT";
        public static final String LOAD_BOOK_LIST = "LOAD BOOK LIST";
    }
}
