class PlaceHold {
  void setSelection(int index, boolean notify) {
    int oldIndex = ((int) (OS.SendMessage(handle, TCM_GETCURSEL, 0, 0)));
    if (oldIndex == index) {
      return;
    }
    if (oldIndex != (-1)) {
      TabItem item = items[oldIndex];
      Control control = item.control;
      if ((control != null) && (!control.isDisposed())) {
        control.setVisible(false);
      }
    }
    OS.SendMessage(handle, TCM_SETCURSEL, index, 0);
    int newIndex = ((int) (OS.SendMessage(handle, TCM_GETCURSEL, 0, 0)));
    if (newIndex != (-1)) {
      TabItem item = items[newIndex];
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
