class PlaceHold {
  public void remove(int index) {
    checkWidget();
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_clist_remove(handle, index);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
