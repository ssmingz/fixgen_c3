class PlaceHold {
  void onFocusIn() {
    if (itemsCount == 0) {
      return;
    }
    if ((style & (SWT.HIDE_SELECTION | SWT.MULTI)) == (SWT.HIDE_SELECTION | SWT.MULTI)) {
      for (int i = 0; i < selectedItems.length; i++) {
        redrawItem(selectedItems[i].index, true);
      }
    }
    if (focusItem != null) {
      redrawItem(focusItem.index, true);
      return;
    }
    TableItem initialFocus;
    if (selectedItems.length > 0) {
      initialFocus = selectedItems[0];
    } else {
      initialFocus = items[topIndex];
      selectItem(initialFocus, false);
    }
    setFocusItem(initialFocus, false);
    redrawItem(initialFocus.index, true);
    Event newEvent = new Event();
    newEvent.item = initialFocus;
    postEvent(Selection, newEvent);
    return;
  }
}
