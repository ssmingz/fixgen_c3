class PlaceHold {
  void createHandle() {
    super.createHandle();
    state &= ~CANVAS;
    int hInstance = OS.GetModuleHandle(null);
    int textExStyle = ((style & SWT.BORDER) != 0) ? OS.WS_EX_CLIENTEDGE : 0;
    int textStyle = (OS.WS_CHILD | OS.WS_VISIBLE) | OS.ES_AUTOHSCROLL;
    if ((style & SWT.READ_ONLY) != 0) {
      textStyle |= OS.ES_READONLY;
    }
    if (OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        textExStyle |= OS.WS_EX_LAYOUTRTL;
      }
    }
    hwndText =
        OS.CreateWindowEx(
            textExStyle, EditClass, null, textStyle, 0, 0, 0, 0, handle, 0, hInstance, null);
    if (hwndText == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.SetWindowLong(hwndText, GWL_ID, hwndText);
    int upDownStyle = ((OS.WS_CHILD | OS.WS_VISIBLE) | OS.UDS_AUTOBUDDY) | OS.UDS_SETBUDDYINT;
    if ((style & SWT.WRAP) != 0) {
      upDownStyle |= OS.UDS_WRAP;
    }
    hwndUpDown =
        OS.CreateWindowEx(
            0, UpDownClass, null, upDownStyle, 0, 0, 0, 0, handle, 0, hInstance, null);
    if (hwndUpDown == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.SetWindowLong(hwndUpDown, GWL_ID, hwndUpDown);
    if (OS.IsDBLocale) {
      int hIMC = OS.ImmGetContext(handle);
      OS.ImmAssociateContext(hwndText, hIMC);
      OS.ImmAssociateContext(hwndUpDown, hIMC);
      OS.ImmReleaseContext(handle, hIMC);
    }
    OS.SendMessage(hwndUpDown, UDM_SETRANGE32, 0, 100);
    OS.SendMessage(hwndUpDown, OS.IsWinCE ? OS.UDM_SETPOS : OS.UDM_SETPOS32, 0, 0);
    pageIncrement = 10;
    TCHAR buffer = new TCHAR(getCodePage(), "0", true);
    OS.SetWindowText(hwndText, buffer);
  }
}
