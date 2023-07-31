class PlaceHold {
  void updateOrientation() {
    int bits = OS.GetWindowLong(handle, GWL_EXSTYLE);
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      bits |= OS.WS_EX_LAYOUTRTL;
    } else {
      bits &= ~OS.WS_EX_LAYOUTRTL;
    }
    OS.SetWindowLong(handle, GWL_EXSTYLE, bits);
    long hwndText = 0;
    long hwndList = 0;
    COMBOBOXINFO pcbi = new COMBOBOXINFO();
    pcbi.cbSize = COMBOBOXINFO.sizeof;
    if (OS.GetComboBoxInfo(handle, pcbi)) {
      hwndText = pcbi.hwndItem;
      hwndList = pcbi.hwndList;
    }
    if (hwndText != 0) {
      int bits1 = OS.GetWindowLong(hwndText, GWL_EXSTYLE);
      int bits2 = OS.GetWindowLong(hwndText, GWL_STYLE);
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits1 |= OS.WS_EX_RIGHT | OS.WS_EX_RTLREADING;
        bits2 |= OS.ES_RIGHT;
      } else {
        bits1 &= ~(OS.WS_EX_RIGHT | OS.WS_EX_RTLREADING);
        bits2 &= ~OS.ES_RIGHT;
      }
      OS.SetWindowLong(hwndText, GWL_EXSTYLE, bits1);
      OS.SetWindowLong(hwndText, GWL_STYLE, bits2);
      RECT rect = new RECT();
      OS.GetWindowRect(hwndText, rect);
      int width = rect.right - rect.left;
      int height = rect.bottom - rect.top;
      OS.GetWindowRect(handle, rect);
      int widthCombo = rect.right - rect.left;
      int heightCombo = rect.bottom - rect.top;
      int uFlags = (OS.SWP_NOMOVE | OS.SWP_NOZORDER) | OS.SWP_NOACTIVATE;
      SetWindowPos(hwndText, 0, 0, 0, width - 1, height - 1, uFlags);
      SetWindowPos(handle, 0, 0, 0, widthCombo - 1, heightCombo - 1, uFlags);
      SetWindowPos(hwndText, 0, 0, 0, width, height, uFlags);
      SetWindowPos(handle, 0, 0, 0, widthCombo, heightCombo, uFlags);
      OS.InvalidateRect(handle, null, true);
    }
    if (hwndList != 0) {
      int bits1 = OS.GetWindowLong(hwndList, GWL_EXSTYLE);
      if ((style & SWT.RIGHT_TO_LEFT) != 0) {
        bits1 |= OS.WS_EX_LAYOUTRTL;
      } else {
        bits1 &= ~OS.WS_EX_LAYOUTRTL;
      }
      OS.SetWindowLong(hwndList, GWL_EXSTYLE, bits1);
    }
  }
}
