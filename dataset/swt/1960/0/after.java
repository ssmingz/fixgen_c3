class PlaceHold {
  void createHandle() {
    super.createHandle();
    state &= ~(CANVAS | THEME_BACKGROUND);
    if (OS.IsPPC) {
      OS.SendMessage(handle, CCM_SETVERSION, 0x20c, 0);
    }
    long hwndToolTip = OS.SendMessage(handle, TCM_GETTOOLTIPS, 0, 0);
    OS.SendMessage(hwndToolTip, TTM_SETMAXTIPWIDTH, 0, 0x7fff);
    createdAsRTL = (style & SWT.RIGHT_TO_LEFT) != 0;
  }
}
