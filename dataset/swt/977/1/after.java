class PlaceHold {
  int gtk_insert_text(int widget, int new_text, int new_text_length, int position) {
    if ((new_text == 0) || (new_text_length == 0)) {
      return 0;
    }
    byte[] buffer = new byte[((int) (new_text_length))];
    OS.memmove(buffer, new_text, buffer.length);
    String oldText = new String(Converter.mbcsToWcs(null, buffer));
    int[] pos = new int[1];
    OS.memmove(pos, position, 4);
    int ptr = OS.gtk_entry_get_text(handle);
    if (pos[0] == (-1)) {
      pos[0] = ((int) (OS.g_utf8_strlen(ptr, -1)));
    }
    int start = ((int) (OS.g_utf16_pointer_to_offset(ptr, pos[0])));
    String newText = verifyText(oldText, start, start);
    if (newText != oldText) {
      int[] newStart = new int[1];
      int[] newEnd = new int[1];
      OS.gtk_editable_get_selection_bounds(handle, newStart, newEnd);
      if (newText != null) {
        if (newStart[0] != newEnd[0]) {
          OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, DELETE_TEXT);
          OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
          OS.gtk_editable_delete_selection(handle);
          OS.g_signal_handlers_unblock_matched(
              handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, DELETE_TEXT);
          OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
        }
        byte[] buffer3 = Converter.wcsToMbcs(null, newText, false);
        OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
        OS.gtk_editable_insert_text(handle, buffer3, buffer3.length, pos);
        OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
        newStart[0] = newEnd[0] = pos[0];
      }
      pos[0] = newEnd[0];
      if (newStart[0] != newEnd[0]) {
        fixStart = newStart[0];
        fixEnd = newEnd[0];
      }
      OS.memmove(position, pos, 4);
      OS.g_signal_stop_emission_by_name(handle, insert_text);
    }
    return 0;
  }
}
