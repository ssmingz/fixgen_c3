class PlaceHold {
  public String getText(int start, int end) {
    checkWidget();
    if (!((start <= end) && (0 <= end))) {
      return "";
    }
    int length = OS.GetWindowTextLength(handle);
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      length = mbcsToWcsPos(length);
    }
    end = Math.min(end, length - 1);
    if (start > end) {
      return "";
    }
    start = Math.max(0, start);
    return getText().substring(start, end + 1);
  }
}
