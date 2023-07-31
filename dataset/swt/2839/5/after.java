class PlaceHold {
  public Point getSelection() {
    checkWidget();
    int[] start = new int[1];
    int[] end = new int[1];
    OS.SendMessage(handle, EM_GETSEL, start, end);
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      start[0] = mbcsToWcsPos(start[0]);
      end[0] = mbcsToWcsPos(end[0]);
    }
    return new Point(start[0], end[0]);
  }
}
