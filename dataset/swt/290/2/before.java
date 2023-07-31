class PlaceHold {
  public void clearAll() {
    checkWidget();
    for (int i = 0; i < itemCount; i++) {
      TableItem item = items[i];
      if (item != null) {
        item.clear();
      }
    }
    if ((currentItem == null) && (drawCount == 0)) {
      OS.UpdateDataBrowserItems(handle, 0, 0, null, kDataBrowserItemNoProperty, kDataBrowserNoItem);
    }
    setScrollWidth(items, true);
  }
}
