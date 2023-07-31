class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    return ((int) (((NSTableView) (view)).rowHeight())) + CELL_GAP;
  }
}
