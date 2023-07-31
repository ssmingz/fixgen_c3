class PlaceHold {
  void onFocusIn() {
    if (items.length == 0) {
      return;
    }
    if (focusItem != null) {
      redrawItem(focusItem.availableIndex, true);
      return;
    }
    TreeItem initialFocus;
    if (selectedItems.length > 0) {
      initialFocus = selectedItems[0];
    } else {
      initialFocus = availableItems[topIndex];
      selectItem(initialFocus, false);
    }
    setFocusItem(initialFocus, false);
    redrawItem(initialFocus.availableIndex, true);
    Event newEvent = new Event();
    newEvent.item = initialFocus;
    sendEvent(Selection, newEvent);
    return;
  }
}
