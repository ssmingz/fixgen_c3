class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    if (text.equals(string)) {
      return;
    }
    super.setText(string);
    int hHeap = OS.GetProcessHeap();
    int pszText = 0;
    boolean success = false;
    if ((OS.IsPPC || OS.IsSP) && (parent.hwndCB != 0)) {
      if (string.indexOf('&') != (-1)) {
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
      TCHAR buffer = new TCHAR(0, string, true);
      int byteCount = buffer.length() * TCHAR.sizeof;
      pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
      OS.MoveMemory(pszText, buffer, byteCount);
      int hwndCB = parent.hwndCB;
      TBBUTTONINFO info2 = new TBBUTTONINFO();
      info2.cbSize = TBBUTTONINFO.sizeof;
      info2.dwMask = OS.TBIF_TEXT;
      info2.pszText = pszText;
      success = OS.SendMessage(hwndCB, TB_SETBUTTONINFO, id, info2) != 0;
    } else {
      MENUITEMINFO info = new MENUITEMINFO();
      info.cbSize = MENUITEMINFO.sizeof;
      int hMenu = parent.handle;
      TCHAR buffer = new TCHAR(0, string, true);
      int byteCount = buffer.length() * TCHAR.sizeof;
      pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
      OS.MoveMemory(pszText, buffer, byteCount);
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(4, 10))) {
        info.fMask = OS.MIIM_STRING;
      } else {
        info.fMask = OS.MIIM_TYPE;
        info.fType = widgetStyle();
      }
      info.dwTypeData = pszText;
      success = OS.SetMenuItemInfo(hMenu, id, false, info);
    }
    if (pszText != 0) {
      OS.HeapFree(hHeap, 0, pszText);
    }
    if (!success) {
      error(ERROR_CANNOT_SET_TEXT);
    }
    parent.redraw();
  }
}
