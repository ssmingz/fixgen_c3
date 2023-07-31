class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    if (string.equals(text)) {
      return;
    }
    super.setText(string);
    int hwnd = parent.handle;
    TBBUTTONINFO info = new TBBUTTONINFO();
    info.cbSize = TBBUTTONINFO.sizeof;
    info.dwMask = OS.TBIF_TEXT | OS.TBIF_STYLE;
    info.fsStyle = ((byte) (widgetStyle() | OS.BTNS_AUTOSIZE));
    int hHeap = OS.GetProcessHeap();
    int pszText = 0;
    if (string.length() != 0) {
      info.fsStyle |= OS.BTNS_SHOWTEXT;
      TCHAR buffer = new TCHAR(parent.getCodePage(), string, true);
      int byteCount = buffer.length() * TCHAR.sizeof;
      pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
      OS.MoveMemory(pszText, buffer, byteCount);
      info.pszText = pszText;
    }
    OS.SendMessage(hwnd, TB_SETBUTTONINFO, id, info);
    if (pszText != 0) {
      OS.HeapFree(hHeap, 0, pszText);
    }
    int hFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
    OS.SendMessage(hwnd, WM_SETFONT, hFont, 0);
    parent.layoutItems();
  }
}
