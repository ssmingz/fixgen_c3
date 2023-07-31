class PlaceHold {
  void createHeaderToolTips() {
    if (OS.IsWinCE) {
      return;
    }
    if (headerToolTipHandle != 0) {
      return;
    }
    headerToolTipHandle =
        OS.CreateWindowEx(
            0,
            new TCHAR(0, OS.TOOLTIPS_CLASS, true),
            null,
            0,
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
