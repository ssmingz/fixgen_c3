class PlaceHold {
  boolean sendKeyEvent(int type, int msg, int wParam, int lParam, Event event) {
    if (!super.sendKeyEvent(type, msg, wParam, lParam, event)) {
      return false;
    }
    if (ignoreVerify) {
      return true;
    }
    if (type != SWT.KeyDown) {
      return true;
    }
    if (((msg != OS.WM_CHAR) && (msg != OS.WM_KEYDOWN)) && (msg != OS.WM_IME_CHAR)) {
      return true;
    }
    if (event.character == 0) {
      return true;
    }
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return true;
    }
    char key = event.character;
    int stateMask = event.stateMask;
    switch (msg) {
      case OS.WM_CHAR:
        if (((((key != 0x8) && (key != 0x7f)) && (key != '\r')) && (key != '\t'))
            && (key != '\n')) {
          break;
        }
      case OS.WM_KEYDOWN:
        if ((stateMask & ((SWT.ALT | SWT.SHIFT) | SWT.CONTROL)) != 0) {
          return false;
        }
        break;
    }
    if (OS.GetKeyState(VK_LBUTTON) < 0) {
      return true;
    }
    String oldText = "";
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    switch (key) {
      case 0x8:
        if (start[0] == end[0]) {
          if (start[0] == 0) {
            return true;
          }
          int lineStart = OS.SendMessage(handle, EM_LINEINDEX, -1, 0);
          if (start[0] == lineStart) {
            start[0] = start[0] - DELIMITER.length();
          } else {
            start[0] = start[0] - 1;
            if (OS.IsDBLocale) {
              int[] newStart = new int[1];
              int[] newEnd = new int[1];
              OS.SendMessage(handle, EM_SETSEL, start[0], end[0]);
              OS.SendMessage(handle, EM_GETSEL, newStart, newEnd);
              if (start[0] != newStart[0]) {
                start[0] = start[0] - 1;
              }
            }
          }
          start[0] = Math.max(start[0], 0);
        }
        break;
      case 0x7f:
        if (start[0] == end[0]) {
          int length = OS.GetWindowTextLength(handle);
          if (start[0] == length) {
            return true;
          }
          int line = OS.SendMessage(handle, EM_LINEFROMCHAR, end[0], 0);
          int lineStart = OS.SendMessage(handle, EM_LINEINDEX, line + 1, 0);
          if (end[0] == (lineStart - DELIMITER.length())) {
            end[0] = end[0] + DELIMITER.length();
          } else {
            end[0] = end[0] + 1;
            if (OS.IsDBLocale) {
              int[] newStart = new int[1];
              int[] newEnd = new int[1];
              OS.SendMessage(handle, EM_SETSEL, start[0], end[0]);
              OS.SendMessage(handle, EM_GETSEL, newStart, newEnd);
              if (end[0] != newEnd[0]) {
                end[0] = end[0] + 1;
              }
            }
          }
          end[0] = Math.min(end[0], length);
        }
        break;
      case '\r':
        if ((style & SWT.MULTI) == 0) {
          return true;
        }
        oldText = DELIMITER;
        break;
      default:
        if ((key != '\t') && (key < 0x20)) {
          return true;
        }
        oldText = new String(new char[] {key});
        break;
    }
    String newText = verifyText(oldText, start[0], end[0], event);
    if (newText == null) {
      return false;
    }
    if (newText == oldText) {
      return true;
    }
    newText = Display.withCrLf(newText);
    TCHAR buffer = new TCHAR(getCodePage(), newText, true);
    OS.SendMessage(handle, EM_SETSEL, start[0], end[0]);
    ignoreCharacter = true;
    OS.SendMessage(handle, EM_REPLACESEL, 0, buffer);
    ignoreCharacter = false;
    return false;
  }
}
