class PlaceHold {
  void onMouseDoubleClick(Event event) {
    if (!isFocusControl()) {
      setFocus();
    }
    int index = ((event.y - getHeaderHeight()) / itemHeight) + topIndex;
    if (!((0 <= index) && (index < availableItems.length))) {
      return;
    }
    TreeItem selectedItem = availableItems[index];
    if (selectedItem != lastClickedItem) {
      return;
    }
    if ((selectedItem.items.length > 0)
        && selectedItem.getExpanderBounds().contains(event.x, event.y)) {
      return;
    }
    if (!selectedItem.getHitBounds().contains(event.x, event.y)) {
      return;
    }
    Event newEvent = new Event();
    newEvent.item = selectedItem;
    postEvent(DefaultSelection, newEvent);
  }
}
