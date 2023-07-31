class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    super.setText(string);
    int hwnd = parent.handle;
    int hHeap = OS.GetProcessHeap();
    TCHAR buffer = new TCHAR(parent.getCodePage(), string, true);
    int byteCount = buffer.length() * TCHAR.sizeof;
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(pszText, buffer, byteCount);
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
    tvItem.hItem = handle;
    tvItem.pszText = pszText;
    OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
    OS.HeapFree(hHeap, 0, pszText);
  }
}
