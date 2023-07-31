class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    NSRect rect = ((NSTableView) (view)).rectOfRow(index);
    ((NSTableView) (view)).scrollRectToVisible(rect);
  }
}
