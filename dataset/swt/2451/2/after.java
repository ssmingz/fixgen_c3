class PlaceHold {
  void setSelection(int index, boolean notify, boolean force) {
    if (index >= OS.GetControl32BitMaximum(handle)) {
      return;
    }
    int currentIndex = OS.GetControl32BitValue(handle) - 1;
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
    OS.SetControl32BitValue(handle, index + 1);
    index = OS.GetControl32BitValue(handle) - 1;
    if (hasFocus()) {
      int window = OS.GetControlOwner(handle);
      OS.SetKeyboardFocus(window, handle, ((short) (index + 1)));
    }
    lastSelected = index;
    if (index != (-1)) {
      TabItem item = items[index];
      if (item != null) {
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setBounds(getClientArea());
          control.setVisible(true);
        }
        if (notify) {
          Event event = new Event();
          event.item = item;
          sendSelectionEvent(Selection, event, true);
        }
      }
    }
  }
}
