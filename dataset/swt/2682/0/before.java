class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    super.setText(string);
    int hHeap = OS.GetProcessHeap();
    TCHAR buffer = new TCHAR(parent.getCodePage(), fixMnemonic(string), true);
    int byteCount = buffer.length() * TCHAR.sizeof;
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(pszText, buffer, byteCount);
    int hwndHeader = parent.hwndHeader;
    if (hwndHeader == 0) {
      return;
    }
    HDITEM hdItem = new HDITEM();
    hdItem.mask = OS.HDI_TEXT;
    hdItem.pszText = pszText;
    int result = OS.SendMessage(hwndHeader, HDM_SETITEM, index, hdItem);
    if (pszText != 0) {
      OS.HeapFree(hHeap, 0, pszText);
    }
    if (result == 0) {
      error(ERROR_CANNOT_SET_TEXT);
    }
  }
}
