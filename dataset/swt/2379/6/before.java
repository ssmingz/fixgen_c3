class PlaceHold {
  public void removeAll() {
    checkWidget();
    items = new String[4];
    itemCount = 0;
    NSTableView widget = ((NSTableView) (view));
    setRedraw(false);
    widget.noteNumberOfRowsChanged();
    widget.tile();
    setRedraw(true);
    setScrollWidth();
  }
}
