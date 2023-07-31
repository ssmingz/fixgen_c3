class PlaceHold {
  @Override
  long windowProc(long hwnd, int msg, long wParam, long lParam) {
    if (((OS.IsUnicode && (handle != 0)) && (lParam != 0)) && ((state & HAS_AUTO_DIRECTION) != 0)) {
      switch (msg) {
        case OS.LB_ADDSTRING:
        case OS.LB_INSERTSTRING:
        case OS.LB_FINDSTRINGEXACT:
          int length = OS.wcslen(lParam);
          int cp = getCodePage();
          TCHAR buffer = new TCHAR(cp, length);
          OS.MoveMemory(buffer, lParam, buffer.length() * TCHAR.sizeof);
          String string = buffer.toString(0, length);
          int direction = resolveTextDirection(string);
          if (direction == SWT.NONE) {
            direction = ((style & SWT.RIGHT_TO_LEFT) != 0) ? SWT.RIGHT_TO_LEFT : SWT.LEFT_TO_RIGHT;
          }
          string = (direction == SWT.RIGHT_TO_LEFT ? RLE : LRE) + string;
          buffer = new TCHAR(cp, string, true);
          long hHeap = OS.GetProcessHeap();
          length = buffer.length() * TCHAR.sizeof;
          long pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, length);
          OS.MoveMemory(pszText, buffer, length);
          long code = super.windowProc(hwnd, msg, wParam, pszText);
          OS.HeapFree(hHeap, 0, pszText);
          addedUCC = true;
          return code;
      }
    }
    return super.windowProc(hwnd, msg, wParam, lParam);
  }
}
