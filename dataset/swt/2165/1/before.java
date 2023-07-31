class PlaceHold {
  int gtk_delete_text(int widget, int start_pos, int end_pos) {
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return 0;
    }
    String newText = verifyText("", ((int) (start_pos)), ((int) (end_pos)));
    if (newText == null) {
      OS.g_signal_stop_emission_by_name(entryHandle, delete_text);
    } else if (newText.length() > 0) {
      int[] pos = new int[1];
      pos[0] = ((int) (end_pos));
      byte[] buffer = Converter.wcsToMbcs(null, newText, false);
      OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
      OS.gtk_editable_insert_text(entryHandle, buffer, buffer.length, pos);
      OS.g_signal_handlers_unblock_matched(
          entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
      OS.g_signal_handlers_unblock_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.gtk_editable_set_position(entryHandle, pos[0]);
    }
    return 0;
  }
}
