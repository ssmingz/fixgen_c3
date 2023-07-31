class PlaceHold {
  int gtk_delete_text(int widget, int start_pos, int end_pos) {
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return 0;
    }
    if (end_pos == (-1)) {
      end_pos = OS.g_utf8_strlen(OS.gtk_entry_get_text(handle), -1);
    }
    String newText = verifyText("", ((int) (start_pos)), ((int) (end_pos)));
    if (newText == null) {
      int[] newStart = new int[1];
      int[] newEnd = new int[1];
      OS.gtk_editable_get_selection_bounds(handle, newStart, newEnd);
      if (newStart[0] != newEnd[0]) {
        fixStart = newStart[0];
        fixEnd = newEnd[0];
      }
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
