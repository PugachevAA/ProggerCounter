package ProggerCounter;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

public class ActiveWindow {
    private String windowName;
    private final String PROG_ALIAS = ".java";
    private boolean codeSign;
    private static final int MAX_TITLE_LENGTH = 1024;
    private int downTime = 0;

    public boolean isCodeSign() {
        return codeSign;
    }

    public void findActiveWindow() {
        char[] buffer = new char[MAX_TITLE_LENGTH];
        long currentTime;
        int delta; //в секундах

        WinDef.HWND hwnd = User32.INSTANCE.GetForegroundWindow();
        WinUser.LASTINPUTINFO lastInputInfo = new WinUser.LASTINPUTINFO();

        User32.INSTANCE.GetLastInputInfo(lastInputInfo);
        currentTime = (System.nanoTime()/1000000);
        downTime = lastInputInfo.dwTime;
        delta = (int) (currentTime - downTime);
        User32.INSTANCE.GetWindowText(hwnd, buffer, MAX_TITLE_LENGTH);
        windowName = Native.toString(buffer);
        if (windowName.contains(PROG_ALIAS) && delta < 10000) {
            codeSign = true;
        } else {
            codeSign = false;
        }

    }
}
