class PlaceHold {
  public int getCaretLineNumber() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, EM_LINEFROMCHAR, -1, 0)));
  }
}
