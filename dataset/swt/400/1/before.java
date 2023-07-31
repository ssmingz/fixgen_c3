class PlaceHold {
  void setSelection(int index, boolean notify) {
    if (index < 0) {
      return;
    }
    int oldIndex = OS.gtk_notebook_get_current_page(handle);
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
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
    OS.gtk_notebook_set_current_page(handle, index);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
    int newIndex = OS.gtk_notebook_get_current_page(handle);
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
        sendEvent(Selection, event);
      }
    }
  }
}
