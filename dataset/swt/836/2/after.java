class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    int[] start = new int[1];
    int[] end = new int[1];
    OS.TXNGetSelection(tx, start, end);
    return end[0] - start[0];
  }
}
