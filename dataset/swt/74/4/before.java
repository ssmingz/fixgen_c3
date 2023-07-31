class PlaceHold {
  int kEventControlHit(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlHit(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int index = OS.GetControl32BitValue(handle) - 1;
    if (index == lastSelected) {
      return result;
    }
    lastSelected = index;
    int count = OS.GetControl32BitMaximum(handle);
    for (int i = 0; i < count; i++) {
      if (i != index) {
        Control control = items[i].control;
        if ((control != null) && (!control.isDisposed())) {
          control.setVisible(false);
        }
      }
    }
    TabItem item = null;
    if (index != (-1)) {
      item = items[index];
    }
    if (item != null) {
      Control control = item.control;
      if ((control != null) && (!control.isDisposed())) {
        control.setBounds(getClientArea());
        control.setVisible(true);
      }
    }
    Event event = new Event();
    event.item = item;
    postEvent(Selection, event);
    return OS.noErr;
  }
}
