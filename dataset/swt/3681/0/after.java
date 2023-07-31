class PlaceHold {
  public void setText(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int itemIndex = parent.indexOf(this);
    if (itemIndex == (-1)) {
      return;
    }
    if (index == 0) {
      if (string.equals(text)) {
        return;
      }
      super.setText(string);
    }
    int hwnd = parent.handle;
    int hHeap = OS.GetProcessHeap();
    TCHAR buffer = new TCHAR(parent.getCodePage(), string, true);
    int byteCount = buffer.length() * TCHAR.sizeof;
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(pszText, buffer, byteCount);
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_TEXT;
    lvItem.iItem = itemIndex;
    lvItem.pszText = pszText;
    lvItem.iSubItem = index;
    OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
    if (index == 0) {
      parent.setScrollWidth();
    }
    OS.HeapFree(hHeap, 0, pszText);
  }
}
