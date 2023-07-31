class PlaceHold {
  public void removeAll() {
    checkWidget();
    for (int i = 0; i < itemCount; i++) {
      TableItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    setTableEmpty();
    NSTableView widget = ((NSTableView) (view));
    setRedraw(false);
    widget.noteNumberOfRowsChanged();
    widget.tile();
    setRedraw(true);
  }
}
