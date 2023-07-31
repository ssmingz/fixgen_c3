class PlaceHold {
  public void clear(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    TableItem item = items[index];
    if (item != null) {
      if (currentItem != item) {
        item.clear();
      }
      if ((currentItem == null) && (drawCount == 0)) {
        int[] id = new int[] {getId(index)};
        OS.UpdateDataBrowserItems(
            handle, 0, id.length, id, kDataBrowserItemNoProperty, kDataBrowserNoItem);
      }
      setScrollWidth(item);
    }
  }
}
