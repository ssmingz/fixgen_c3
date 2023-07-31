class PlaceHold {
  public Point getCaretLocation() {
    checkWidget();
    int position;
    if (textVerify != null) {
      position = textVerify.currInsert;
    } else {
      position = OS.XmTextGetInsertionPosition(handle);
    }
    short[] x = new short[1];
    short[] y = new short[1];
    OS.XmTextPosToXY(handle, position, x, y);
    return new Point(x[0], y[0] - getFontAscent(handle));
  }
}
