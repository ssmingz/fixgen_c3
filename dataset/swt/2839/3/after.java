class PlaceHold {
  boolean sendKeyEvent(int type, int msg, int wParam, int lParam, Event event) {
    if (!super.sendKeyEvent(type, msg, wParam, lParam, event)) {
      return false;
    }
    if ((style & SWT.READ_ONLY) != 0) {
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
    OS.SendMessage(hwndText, EM_GETSEL, start, end);
    switch (key) {
      case 0x8:
        if (start[0] == end[0]) {
          if (start[0] == 0) {
            return true;
          }
          start[0] = start[0] - 1;
          if ((!OS.IsUnicode) && OS.IsDBLocale) {
            int[] newStart = new int[1];
            int[] newEnd = new int[1];
            OS.SendMessage(hwndText, EM_SETSEL, start[0], end[0]);
            OS.SendMessage(hwndText, EM_GETSEL, newStart, newEnd);
            if (start[0] != newStart[0]) {
              start[0] = start[0] - 1;
            }
          }
          start[0] = Math.max(start[0], 0);
        }
        break;
      case 0x7f:
        if (start[0] == end[0]) {
          int length = OS.GetWindowTextLength(hwndText);
          if (start[0] == length) {
            return true;
          }
          end[0] = end[0] + 1;
          if (OS.IsDBLocale) {
            int[] newStart = new int[1];
            int[] newEnd = new int[1];
            OS.SendMessage(hwndText, EM_SETSEL, start[0], end[0]);
            OS.SendMessage(hwndText, EM_GETSEL, newStart, newEnd);
            if (end[0] != newEnd[0]) {
              end[0] = end[0] + 1;
            }
          }
          end[0] = Math.min(end[0], length);
        }
        break;
      case '\r':
        return true;
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
    TCHAR buffer = new TCHAR(getCodePage(), newText, true);
    OS.SendMessage(hwndText, EM_SETSEL, start[0], end[0]);
    OS.SendMessage(hwndText, EM_REPLACESEL, 0, buffer);
    return false;
  }
}
