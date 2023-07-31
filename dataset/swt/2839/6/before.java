class PlaceHold {
  public int getCharCount() {
    checkWidget();
    int length = OS.GetWindowTextLength(handle);
    if (OS.IsDBLocale) {
      length = mbcsToWcsPos(length);
    }
    return length;
  }
}
