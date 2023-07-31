class PlaceHold {
  void createHandle() {
    super.createHandle();
    state &= ~(CANVAS | THEME_BACKGROUND);
    if (EXPLORER_THEME) {
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
        explorerTheme = true;
        OS.SetWindowTheme(handle, EXPLORER, null);
      }
    }
    if (HeaderProc == 0) {
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      HeaderProc = OS.GetWindowLongPtr(hwndHeader, GWLP_WNDPROC);
    }
    if (!OS.IsWinCE) {
      if (OS.COMCTL32_MAJOR < 6) {
        OS.SendMessage(handle, CCM_SETVERSION, 5, 0);
      }
    }
    if ((style & SWT.CHECK) != 0) {
      int empty = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 0, 0);
      int oneItem = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 1, 0);
      int width = OS.HIWORD(oneItem) - OS.HIWORD(empty);
      int height = width;
      setCheckboxImageList(width, height, false);
      OS.SendMessage(handle, LVM_SETCALLBACKMASK, LVIS_STATEIMAGEMASK, 0);
    }
    int hFont = OS.GetStockObject(SYSTEM_FONT);
    OS.SendMessage(handle, WM_SETFONT, hFont, 0);
    LVCOLUMN lvColumn = new LVCOLUMN();
    lvColumn.mask = OS.LVCF_TEXT | OS.LVCF_WIDTH;
    int hHeap = OS.GetProcessHeap();
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, sizeof);
    lvColumn.pszText = pszText;
    OS.SendMessage(handle, LVM_INSERTCOLUMN, 0, lvColumn);
    OS.HeapFree(hHeap, 0, pszText);
    int bits1 = OS.LVS_EX_LABELTIP;
    if ((style & SWT.FULL_SELECTION) != 0) {
      bits1 |= OS.LVS_EX_FULLROWSELECT;
    }
    if (OS.COMCTL32_MAJOR >= 6) {
      bits1 |= OS.LVS_EX_DOUBLEBUFFER;
    }
    OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits1, bits1);
    if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
      return;
    }
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      int bits2 = OS.GetWindowLong(hwndHeader, GWL_EXSTYLE);
      OS.SetWindowLong(hwndHeader, GWL_EXSTYLE, bits2 | OS.WS_EX_LAYOUTRTL);
    }
  }
}
