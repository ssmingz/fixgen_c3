class PlaceHold {
  public void deselect(int start, int end) {
    checkWidget();
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    for (int index = start; index <= end; index++) {
      if ((index < 0) || (index >= itemCount)) {
        continue;
      }
      OS.gtk_tree_selection_unselect_iter(selection, items[index].handle);
    }
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
  }
}
