class PlaceHold {
  public int getCharCount() {
    checkWidget();
    int length = OS.GetWindowTextLength(handle);
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      length = mbcsToWcsPos(length);
    }
    return length;
  }
}
