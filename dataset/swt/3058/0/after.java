class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.READ_ONLY) != 0) {
      int index = indexOf(string);
      if (index == (-1)) {
        return;
      }
      if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
        OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
        OS.gtk_combo_box_set_active(handle, index);
        OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
        sendEvent(Modify);
        return;
      }
    }
    if (hooks(Verify) || filters(Verify)) {
      int ptr = OS.gtk_entry_get_text(entryHandle);
      string = verifyText(string, 0, ((int) (OS.g_utf8_strlen(ptr, -1))));
      if (string == null) {
        return;
      }
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    }
    OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, DELETE_TEXT);
    OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
    OS.gtk_entry_set_text(entryHandle, buffer);
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    }
    OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, DELETE_TEXT);
    OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
    sendEvent(Modify);
  }
}
