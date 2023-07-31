class PlaceHold {
  public Point getSelection() {
    checkWidget();
    int[] start = new int[1];
    int[] end = new int[1];
    OS.TXNGetSelection(fTX, start, end);
    return new Point(start[0], end[0]);
  }
}
