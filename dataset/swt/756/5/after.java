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
      select(index);
      return;
    }
    if (hooks(Verify) || filters(Verify)) {
      long ptr = OS.gtk_entry_get_text(entryHandle);
      string = verifyText(string, 0, ((int) (OS.g_utf16_strlen(ptr, -1))));
      if (string == null) {
        return;
      }
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, DELETE_TEXT);
    OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
    OS.gtk_entry_set_text(entryHandle, buffer);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, DELETE_TEXT);
    OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
    sendEvent(Modify);
  }
}
