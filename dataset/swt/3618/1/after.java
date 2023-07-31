class PlaceHold {
  void doFocusIn() {
    if (getItemCount() == 0) {
      return;
    }
    if (focusItem == null) {
      TreeItem2 item = availableItems[0];
      selectItem(item, false);
      setFocusItem(item, false);
      Event newEvent = new Event();
      newEvent.item = item;
      sendEvent(Selection, newEvent);
      if (isDisposed()) {
        return;
      }
      showItem(item);
    }
    redrawItem(focusItem.availableIndex);
  }
}
