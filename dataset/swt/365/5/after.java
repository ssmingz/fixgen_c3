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
    boolean fixPPCMenuBar = false;
    if (OS.IsPPC) {
      Decorations shell = parent.parent;
      if (parent == shell.menuBar) {
        fixPPCMenuBar = true;
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
      }
    }
    int hMenu = parent.handle;
    int hHeap = OS.GetProcessHeap();
    TCHAR buffer = new TCHAR(0, string, true);
    int byteCount = buffer.length() * TCHAR.sizeof;
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
    OS.MoveMemory(pszText, buffer, byteCount);
    MENUITEMINFO info = new MENUITEMINFO();
    info.cbSize = MENUITEMINFO.sizeof;
    info.fMask = OS.MIIM_TYPE;
    info.fType = widgetStyle();
    info.dwTypeData = pszText;
    boolean success = OS.SetMenuItemInfo(hMenu, id, false, info);
    if (!OS.IsWinCE) {
      if (((OS.WIN32_MAJOR << 16) | OS.WIN32_MINOR) >= ((4 << 16) | 10)) {
        if (image != null) {
          info.fMask = OS.MIIM_BITMAP;
          info.hbmpItem = OS.HBMMENU_CALLBACK;
          success = OS.SetMenuItemInfo(hMenu, id, false, info);
        }
      }
    }
    if (fixPPCMenuBar) {
      Decorations shell = parent.parent;
      TBBUTTONINFO info2 = new TBBUTTONINFO();
      info2.cbSize = TBBUTTONINFO.sizeof;
      info2.dwMask = OS.TBIF_TEXT;
      info2.pszText = pszText;
      OS.SendMessage(shell.hwndTB, TB_SETBUTTONINFO, id, info2);
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
