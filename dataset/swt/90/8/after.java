class PlaceHold {
  LRESULT WM_COMMAND(int wParam, int lParam) {
    if (lParam == 0) {
      Decorations shell = menuShell();
      if (shell.isEnabled()) {
        int id = OS.LOWORD(wParam);
        MenuItem item = display.getMenuItem(id);
        if ((item != null) && item.isEnabled()) {
          return item.wmCommandChild(wParam, lParam);
        }
      }
      return null;
    }
    Control control = display.getControl(lParam);
    if (control == null) {
      return null;
    }
    return control.wmCommandChild(wParam, lParam);
  }
}
