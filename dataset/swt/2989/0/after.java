class PlaceHold {
  public void removeAll() {
    checkWidget();
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    items = new TreeItem[4];
    itemCount = 0;
    ((NSOutlineView) (view)).reloadData();
    setScrollWidth();
  }
}
