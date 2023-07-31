class PlaceHold {
  void createHandle() {
    super.createHandle();
    state &= ~CANVAS;
    if ((style & SWT.CHECK) != 0) {
      setCheckboxImageList();
    }
    int hFont = OS.GetStockObject(SYSTEM_FONT);
    OS.SendMessage(handle, WM_SETFONT, hFont, 0);
    LVCOLUMN lvColumn = new LVCOLUMN();
    lvColumn.mask = OS.LVCF_TEXT;
    int hHeap = OS.GetProcessHeap();
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, sizeof);
    lvColumn.pszText = pszText;
    OS.SendMessage(handle, LVM_INSERTCOLUMN, 0, lvColumn);
    OS.HeapFree(hHeap, 0, pszText);
    int bits = OS.LVS_EX_SUBITEMIMAGES | OS.LVS_EX_LABELTIP;
    if ((style & SWT.FULL_SELECTION) != 0) {
      bits |= OS.LVS_EX_FULLROWSELECT;
    }
    OS.SendMessage(handle, LVM_SETEXTENDEDLISTVIEWSTYLE, bits, bits);
  }
}
