class PlaceHold {
  int getPosition(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int lParam = OS.MAKELPARAM(point.x, point.y);
    int position = OS.LOWORD(OS.SendMessage(handle, EM_CHARFROMPOS, 0, lParam));
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      position = mbcsToWcsPos(position);
    }
    if (segments != null) {
      position = untranslateOffset(position);
    }
    return position;
  }
}
