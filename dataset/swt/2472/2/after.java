class PlaceHold {
  void reassignFocus() {
    if (focusItem == null) {
      return;
    }
    if (focusItem.parentItem != null) {
      TreeItem item = focusItem.getParentItem();
      setFocusItem(item, false);
      showItem(item);
      if ((style & SWT.MULTI) != 0) {
        return;
      }
      setSelection(new TreeItem[] {item});
      Event event = new Event();
      event.item = item;
      sendEvent(Selection, event);
      return;
    }
    int index = focusItem.getIndex();
    if (index != 0) {
      index--;
    } else {
      index++;
    }
    if (index < items.length) {
      TreeItem item = items[index];
      setFocusItem(item, false);
      showItem(item);
      if ((style & SWT.SINGLE) != 0) {
        setSelection(new TreeItem[] {item});
        Event event = new Event();
        event.item = item;
        sendEvent(Selection, event);
      }
    } else {
      setFocusItem(null, false);
    }
  }
}
