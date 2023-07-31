class PlaceHold {
  public void setSelection(Point selection) {
    checkWidget();
    if (selection == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int start = selection.x;
    int end = selection.y;
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      start = wcsToMbcsPos(start);
      end = wcsToMbcsPos(end);
    }
    int bits = OS.MAKELPARAM(start, end);
    OS.SendMessage(handle, CB_SETEDITSEL, 0, bits);
  }
}
