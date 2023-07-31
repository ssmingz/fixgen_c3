class PlaceHold {
  void createHeaderToolTips() {
    if (OS.IsWinCE) {
      return;
    }
    if (headerToolTipHandle != 0) {
      return;
    }
    int bits = 0;
    if (OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits |= OS.WS_EX_LAYOUTRTL;
      }
    }
    headerToolTipHandle =
        OS.CreateWindowEx(
            bits,
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
    if (headerToolTipHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.SendMessage(headerToolTipHandle, TTM_SETMAXTIPWIDTH, 0, 0x7fff);
  }
}
