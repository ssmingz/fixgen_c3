class PlaceHold {
  void createItemToolTips() {
    if (OS.IsWinCE) {
      return;
    }
    if (itemToolTipHandle != 0) {
      return;
    }
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    bits |= OS.TVS_NOTOOLTIPS;
    OS.SetWindowLong(handle, GWL_STYLE, bits);
    itemToolTipHandle =
        OS.CreateWindowEx(
            WS_EX_TRANSPARENT,
            new TCHAR(0, OS.TOOLTIPS_CLASS, true),
            null,
            TTS_NOPREFIX,
            CW_USEDEFAULT,
            0,
            CW_USEDEFAULT,
            0,
            handle,
            0,
            OS.GetModuleHandle(null),
            null);
    if (itemToolTipHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.SendMessage(itemToolTipHandle, TTM_SETDELAYTIME, TTDT_INITIAL, 0);
    TOOLINFO lpti = new TOOLINFO();
    lpti.cbSize = TOOLINFO.sizeof;
    lpti.hwnd = handle;
    lpti.uId = handle;
    lpti.uFlags = OS.TTF_SUBCLASS | OS.TTF_TRANSPARENT;
    lpti.lpszText = OS.LPSTR_TEXTCALLBACK;
    OS.SendMessage(itemToolTipHandle, TTM_ADDTOOL, 0, lpti);
  }
}
