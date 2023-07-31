class PlaceHold {
  LRESULT wmKeyDown(int hwnd, int wParam, int lParam) {
    LRESULT result = super.wmKeyDown(hwnd, wParam, lParam);
    if (result != null) {
      return result;
    }
    UDACCEL udaccel = new UDACCEL();
    OS.SendMessage(hwndUpDown, UDM_GETACCEL, 1, udaccel);
    int delta = 0;
    switch (wParam) {
      case OS.VK_UP:
        delta = udaccel.nInc;
        break;
      case OS.VK_DOWN:
        delta = -udaccel.nInc;
        break;
      case OS.VK_PRIOR:
        delta = pageIncrement;
        break;
      case OS.VK_NEXT:
        delta = -pageIncrement;
        break;
    }
    if (delta != 0) {
      int value = getSelectionText();
      if (value != (-1)) {
        if (OS.IsWinCE) {
          value = OS.LOWORD(OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0));
        } else {
          value = OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0);
        }
      }
      int newValue = value + delta;
      int[] max = new int[1];
      int[] min = new int[1];
      OS.SendMessage(hwndUpDown, UDM_GETRANGE32, min, max);
      if ((style & SWT.WRAP) != 0) {
        if (newValue < min[0]) {
          newValue = max[0];
        }
        if (newValue > max[0]) {
          newValue = min[0];
        }
      }
      newValue = Math.min(Math.max(min[0], newValue), max[0]);
      if (value != newValue) {
        setSelection(newValue, true, true, true);
      }
    }
    switch (wParam) {
      case OS.VK_UP:
      case OS.VK_DOWN:
        return LRESULT.ZERO;
    }
    return result;
  }
}
