class PlaceHold {
  public void clearAll() {
    checkWidget();
    for (int i = 0; i < itemCount; i++) {
      TableItem item = items[i];
      if (item != null) {
        item.clear();
      }
    }
    NSTableView widget = ((NSTableView) (view));
    widget.reloadData();
  }
}
