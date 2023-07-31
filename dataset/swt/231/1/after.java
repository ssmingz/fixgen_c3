class PlaceHold {
  void subclass() {
    super.subclass();
    if (ToolTipProc != 0) {
      long newProc = display.windowProc;
      if (toolTipHandle != 0) {
        OS.SetWindowLongPtr(toolTipHandle, GWLP_WNDPROC, newProc);
      }
      if (balloonTipHandle != 0) {
        OS.SetWindowLongPtr(balloonTipHandle, GWLP_WNDPROC, newProc);
      }
      if (menuItemToolTipHandle != 0) {
        OS.SetWindowLongPtr(menuItemToolTipHandle, GWLP_WNDPROC, newProc);
      }
    }
  }
}
