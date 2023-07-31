class PlaceHold {
  void createItem(TabItem item, int index) {
    int count = OS.GetControl32BitMaximum(handle);
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    OS.SetControl32BitMaximum(handle, count + 1);
    if (count == items.length) {
      TabItem[] newItems = new TabItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    System.arraycopy(items, index, items, index + 1, count - index);
    items[index] = item;
    if (count == 0) {
      OS.SetControl32BitValue(handle, 1);
      lastSelected = 0;
      Event event = new Event();
      event.item = items[0];
      sendSelectionEvent(Selection, event, true);
    }
  }
}
