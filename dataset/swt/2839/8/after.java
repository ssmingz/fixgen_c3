class PlaceHold {
  public void setSelection(int start) {
    checkWidget();
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      start = wcsToMbcsPos(start);
    }
    OS.SendMessage(handle, EM_SETSEL, start, start);
    OS.SendMessage(handle, EM_SCROLLCARET, 0, 0);
  }
}
