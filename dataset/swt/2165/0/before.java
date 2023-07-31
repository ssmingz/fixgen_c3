class PlaceHold {
  int gtk_insert_text(int widget, int new_text, int new_text_length, int position) {
    if ((!hooks(Verify)) && (!filters(Verify))) {
      return 0;
    }
    if ((new_text == 0) || (new_text_length == 0)) {
      return 0;
    }
    byte[] buffer = new byte[((int) (new_text_length))];
    OS.memmove(buffer, new_text, buffer.length);
    String oldText = new String(Converter.mbcsToWcs(null, buffer));
    int[] pos = new int[1];
    OS.memmove(pos, position, 4);
    if (pos[0] == (-1)) {
      int ptr = OS.gtk_entry_get_text(entryHandle);
      pos[0] = ((int) (OS.g_utf8_strlen(ptr, -1)));
    }
    String newText = verifyText(oldText, pos[0], pos[0]);
    if (newText == null) {
      OS.g_signal_stop_emission_by_name(entryHandle, insert_text);
    } else if (newText != oldText) {
      byte[] buffer2 = Converter.wcsToMbcs(null, newText, false);
      OS.g_signal_handlers_block_matched(entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
      OS.gtk_editable_insert_text(entryHandle, buffer2, buffer2.length, pos);
      OS.g_signal_handlers_unblock_matched(
          entryHandle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, INSERT_TEXT);
      OS.g_signal_stop_emission_by_name(entryHandle, insert_text);
      OS.memmove(position, pos, 4);
    }
    return 0;
  }
}
