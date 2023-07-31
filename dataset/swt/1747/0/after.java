class PlaceHold {
  public int getCaretPosition() {
    checkWidget();
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    int caret = start[0];
    if (start[0] != end[0]) {
      int startLine = ((int) (OS.SendMessage(handle, EM_LINEFROMCHAR, start[0], 0)));
      int endLine = ((int) (OS.SendMessage(handle, EM_LINEFROMCHAR, end[0], 0)));
      if (startLine == endLine) {
        if (!OS.IsWinCE) {
          int idThread = OS.GetWindowThreadProcessId(handle, null);
          GUITHREADINFO lpgui = new GUITHREADINFO();
          lpgui.cbSize = GUITHREADINFO.sizeof;
          if (OS.GetGUIThreadInfo(idThread, lpgui)) {
            if ((lpgui.hwndCaret == handle) || (lpgui.hwndCaret == 0)) {
              POINT ptCurrentPos = new POINT();
              if (OS.GetCaretPos(ptCurrentPos)) {
                int endPos = OS.SendMessage(handle, EM_POSFROMCHAR, end[0], 0);
                if (endPos == (-1)) {
                  int startPos = OS.SendMessage(handle, EM_POSFROMCHAR, start[0], 0);
                  int startX = OS.GET_X_LPARAM(startPos);
                  if (ptCurrentPos.x > startX) {
                    caret = end[0];
                  }
                } else {
                  int endX = OS.GET_X_LPARAM(endPos);
                  if (ptCurrentPos.x >= endX) {
                    caret = end[0];
                  }
                }
              }
            }
          }
        }
      } else {
        int caretPos = ((int) (OS.SendMessage(handle, EM_LINEINDEX, -1, 0)));
        int caretLine = ((int) (OS.SendMessage(handle, EM_LINEFROMCHAR, caretPos, 0)));
        if (caretLine == endLine) {
          caret = end[0];
        }
      }
    }
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      caret = mbcsToWcsPos(caret);
    }
    if (segments != null) {
      caret = untranslateOffset(caret);
    }
    return caret;
  }
}
