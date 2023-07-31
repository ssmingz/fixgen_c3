class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    return ((int) (((NSTableView) (view)).numberOfSelectedRows()));
  }
}
