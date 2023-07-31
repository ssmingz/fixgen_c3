class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (string.equals(text)) {
      return;
    }
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    super.setText(string);
    int hwnd = parent.handle;
    LVCOLUMN lvColumn = new LVCOLUMN();
    lvColumn.mask = OS.LVCF_FMT;
    OS.SendMessage(hwnd, LVM_GETCOLUMN, index, lvColumn);
    int hHeap = OS.GetProcessHeap();
    TCHAR buffer = new TCHAR(parent.getCodePage(), fixMnemonic(string), true);
    int byteCount = buffer.length() * TCHAR.sizeof;
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(pszText, buffer, byteCount);
    lvColumn.mask |= OS.LVCF_TEXT;
    lvColumn.pszText = pszText;
    int result = OS.SendMessage(hwnd, LVM_SETCOLUMN, index, lvColumn);
    if (pszText != 0) {
      OS.HeapFree(hHeap, 0, pszText);
    }
    if (result == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
  }
}
