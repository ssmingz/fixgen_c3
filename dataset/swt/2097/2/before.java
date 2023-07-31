class PlaceHold {
  void setSelection(int index, boolean notify, boolean force) {
    if (!((0 <= index) && (index < itemCount))) {
      return;
    }
    int currentIndex = getSelectionIndex();
    if ((!force) && (currentIndex == index)) {
      return;
    }
    if (currentIndex != (-1)) {
      TabItem item = items[currentIndex];
      if (item != null) {
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setVisible(false);
        }
      }
    }
    ((NSTabView) (view)).selectTabViewItemAtIndex(index);
    index = getSelectionIndex();
    if (index != (-1)) {
      TabItem item = items[index];
      if (item != null) {
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setVisible(true);
        }
        if (notify) {
          Event event = new Event();
          event.item = item;
          sendEvent(Selection, event);
        }
      }
    }
  }
}
