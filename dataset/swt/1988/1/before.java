class PlaceHold {
  public void setItem(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_clist_set_text(handle, index, 0, buffer);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
