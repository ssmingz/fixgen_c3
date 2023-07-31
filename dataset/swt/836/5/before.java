class PlaceHold {
  public int getCaretPosition() {
    checkWidget();
    int[] start = new int[1];
    int[] end = new int[1];
    OS.TXNGetSelection(fTX, start, end);
    return end[0];
  }
}
