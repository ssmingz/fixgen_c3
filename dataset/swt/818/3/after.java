class PlaceHold {
  void destroyItem(TabItem item) {
    int index = 0;
    int itemCount = getItemCount();
    while (index < itemCount) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    if (index == itemCount) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    int oldIndex = OS.gtk_notebook_get_current_page(handle);
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
    OS.gtk_notebook_remove_page(handle, index);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
    if (index == oldIndex) {
      int newIndex = OS.gtk_notebook_get_current_page(handle);
      if (newIndex != (-1)) {
        Control control = items[newIndex].getControl();
        if ((control != null) && (!control.isDisposed())) {
          control.setBounds(getClientArea());
          control.setVisible(true);
        }
        Event event = new Event();
        event.item = items[newIndex];
        sendSelectionEvent(Selection, event, true);
      }
    }
  }
}
