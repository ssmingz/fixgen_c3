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
    if ((OS.COMCTL32_MAJOR >= 6) && (image != null)) {
      if (text.indexOf('&') != (-1)) {
        int length = string.length();
        char[] text = new char[length];
        string.getChars(0, length, text, 0);
        int i = 0;
        int j = 0;
        for (i = 0; i < length; i++) {
          if (text[i] != '&') {
            text[j++] = text[i];
          }
        }
        if (j < i) {
          string = new String(text, 0, j);
        }
      }
    }
    int hwnd = parent.handle;
    int hHeap = OS.GetProcessHeap();
    TCHAR buffer = new TCHAR(parent.getCodePage(), string, true);
    int byteCount = buffer.length() * TCHAR.sizeof;
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(pszText, buffer, byteCount);
    TCITEM tcItem = new TCITEM();
    tcItem.mask = OS.TCIF_TEXT;
    tcItem.pszText = pszText;
    OS.SendMessage(hwnd, TCM_SETITEM, index, tcItem);
    OS.HeapFree(hHeap, 0, pszText);
  }
}
