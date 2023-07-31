class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (!(((0 < start) && (start <= end)) && (end < OS.GTK_CLIST_ROWS(handle)))) {
      error(ERROR_INVALID_RANGE);
    }
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    int index = start;
    while (index <= end) {
      OS.gtk_clist_remove(handle, start);
      index++;
    }
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
