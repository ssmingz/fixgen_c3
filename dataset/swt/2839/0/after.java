class PlaceHold {
  public int getCaretPosition() {
    checkWidget();
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    int startLine = OS.SendMessage(handle, EM_LINEFROMCHAR, start[0], 0);
    int caretPos = OS.SendMessage(handle, EM_LINEINDEX, -1, 0);
    int caretLine = OS.SendMessage(handle, EM_LINEFROMCHAR, caretPos, 0);
    int caret = end[0];
    if (caretLine == startLine) {
      caret = start[0];
    }
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      caret = mbcsToWcsPos(caret);
    }
    return caret;
  }
}
