class PlaceHold {
  public void removeAll() {
    checkWidget();
    OS.gtk_tree_store_clear(modelHandle);
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseResources();
      }
    }
    items = new TreeItem[4];
  }
}
