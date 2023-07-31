class PlaceHold {
  public void removeAll() {
    checkWidget();
    OS.gtk_ctree_remove_node(handle, 0);
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseResources();
      }
    }
    items = new TreeItem[4];
  }
}
