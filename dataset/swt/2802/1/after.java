class PlaceHold {
  LRESULT wmClipboard(int hwndText, int msg, int wParam, int lParam) {
    if ((style & SWT.READ_ONLY) != 0) {
      return null;
    }
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return null;
    }
    boolean call = false;
    int[] start = new int[1];
    int[] end = new int[1];
    String oldText = null;
    String newText = null;
    switch (msg) {
      case OS.WM_CLEAR:
      case OS.WM_CUT:
        OS.SendMessage(hwndText, EM_GETSEL, start, end);
        if (start[0] != end[0]) {
          newText = "";
          call = true;
        }
        break;
      case OS.WM_PASTE:
        OS.SendMessage(hwndText, EM_GETSEL, start, end);
        newText = getClipboardText();
        break;
      case OS.EM_UNDO:
      case OS.WM_UNDO:
        if (OS.SendMessage(hwndText, EM_CANUNDO, 0, 0) != 0) {
          ignoreModify = true;
          OS.SendMessage(hwndText, EM_GETSEL, start, end);
          if (OS.IsDBLocale) {
            start[0] = mbcsToWcsPos(start[0]);
            end[0] = mbcsToWcsPos(end[0]);
          }
          OS.CallWindowProc(EditProc, hwndText, msg, wParam, lParam);
          int length = OS.GetWindowTextLength(hwndText);
          if ((length != 0) && (start[0] != end[0])) {
            TCHAR buffer = new TCHAR(getCodePage(), length + 1);
            OS.GetWindowText(hwndText, buffer, length + 1);
            newText = buffer.toString(start[0], end[0] - start[0]);
          } else {
            newText = "";
          }
          OS.CallWindowProc(EditProc, hwndText, msg, wParam, lParam);
          ignoreModify = false;
        }
        break;
    }
    if ((newText != null) && (!newText.equals(oldText))) {
      oldText = newText;
      newText = verifyText(newText, start[0], end[0], null, false);
      if (newText == null) {
        return LRESULT.ZERO;
      }
      if (!newText.equals(oldText)) {
        if (call) {
          OS.CallWindowProc(EditProc, hwndText, msg, wParam, lParam);
        }
        TCHAR buffer = new TCHAR(getCodePage(), newText, true);
        if (msg == OS.WM_SETTEXT) {
          int hHeap = OS.GetProcessHeap();
          int byteCount = buffer.length() * TCHAR.sizeof;
          int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
          OS.MoveMemory(pszText, buffer, byteCount);
          int code = OS.CallWindowProc(EditProc, hwndText, msg, wParam, pszText);
          OS.HeapFree(hHeap, 0, pszText);
          return new LRESULT(code);
        } else {
          OS.SendMessage(hwndText, EM_REPLACESEL, 0, buffer);
          return LRESULT.ZERO;
        }
      }
    }
    return null;
  }
}
