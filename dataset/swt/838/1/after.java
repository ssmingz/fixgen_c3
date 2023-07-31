class PlaceHold {
  public void selectAll() {
    checkWidget();
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_ctree_select_recursive(handle, 0);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
