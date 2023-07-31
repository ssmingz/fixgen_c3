class PlaceHold {
  void createHandle() {
    super.createHandle();
    state &= ~(CANVAS | THEME_BACKGROUND);
    if (EXPLORER_THEME) {
      if (((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) && OS.IsAppThemed()) {
        explorerTheme = true;
        OS.SetWindowTheme(handle, EXPLORER, null);
        int bits = (OS.TVS_EX_DOUBLEBUFFER | OS.TVS_EX_FADEINOUTEXPANDOS) | OS.TVS_EX_RICHTOOLTIP;
        if ((style & SWT.FULL_SELECTION) == 0) {
          bits |= OS.TVS_EX_AUTOHSCROLL;
        }
        OS.SendMessage(handle, TVM_SETEXTENDEDSTYLE, 0, bits);
        setForegroundPixel(-1);
      }
    }
    if (!OS.IsWinCE) {
      if (OS.COMCTL32_MAJOR < 6) {
        OS.SendMessage(handle, CCM_SETVERSION, 5, 0);
      }
    }
    if ((style & SWT.CHECK) != 0) {
      setCheckboxImageList();
    }
    int hFont = OS.GetStockObject(SYSTEM_FONT);
    OS.SendMessage(handle, WM_SETFONT, hFont, 0);
  }
}
