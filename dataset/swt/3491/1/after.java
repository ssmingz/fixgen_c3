class PlaceHold {
  LRESULT WM_SETCURSOR(int wParam, int lParam) {
    int msg = lParam >> 16;
    if (msg == OS.WM_LBUTTONDOWN) {
      if (!Display.TrimEnabled) {
        Shell modalShell = display.getModalShell();
        if ((modalShell != null) && (!isActive())) {
          int hwndModal = modalShell.handle;
          if (OS.IsWindowEnabled(hwndModal)) {
            OS.SetActiveWindow(hwndModal);
          }
        }
      }
      if (!OS.IsWindowEnabled(handle)) {
        if (!OS.IsWinCE) {
          int hwndPopup = OS.GetLastActivePopup(handle);
          if ((hwndPopup != 0) && (hwndPopup != handle)) {
            if (WidgetTable.get(hwndPopup) == null) {
              if (OS.IsWindowEnabled(hwndPopup)) {
                OS.SetActiveWindow(hwndPopup);
              }
            }
          }
        }
      }
    }
    return super.WM_SETCURSOR(wParam, lParam);
  }
}
