class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    return ((int) (((NSOutlineView) (view)).numberOfSelectedRows()));
  }
}
