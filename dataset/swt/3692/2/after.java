class PlaceHold {
  int gtk_delete_text(int widget, int start_pos, int end_pos) {
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return 0;
    }
    int ptr = OS.gtk_entry_get_text(handle);
    if (end_pos == (-1)) {
      end_pos = OS.g_utf8_strlen(ptr, -1);
    }
    int start = ((int) (OS.g_utf8_offset_to_utf16_offset(ptr, start_pos)));
    int end = ((int) (OS.g_utf8_offset_to_utf16_offset(ptr, end_pos)));
    String newText = verifyText("", start, end);
    if (newText == null) {
      OS.g_signal_stop_emission_by_name(handle, delete_text);
    } else if (newText.length() > 0) {
      int[] pos = new int[1];
      pos[0] = ((int) (end_pos));
      byte[] buffer = Converter.wcsToMbcs(null, newText, false);
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
      OS.gtk_editable_insert_text(handle, buffer, buffer.length, pos);
      OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
      OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.gtk_editable_set_position(handle, pos[0]);
    }
    return 0;
  }
}
