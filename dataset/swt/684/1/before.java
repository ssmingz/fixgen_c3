class PlaceHold {
  public String open() {
    byte[] titleBytes = Converter.wcsToMbcs(null, title, true);
    handle = OS.gtk_file_selection_new(titleBytes);
    if (parent != null) {
      OS.gtk_window_set_transient_for(handle, parent.topHandle());
    }
    String answer = null;
    preset();
    int response = OS.gtk_dialog_run(handle);
    if (response == OS.GTK_RESPONSE_OK) {
      int fileNamePtr = OS.gtk_file_selection_get_filename(handle);
      int utf8Ptr = OS.g_filename_to_utf8(fileNamePtr, -1, null, null, null);
      int[] items_written = new int[1];
      int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
      int length = items_written[0];
      char[] buffer = new char[length];
      OS.memmove(buffer, utf16Ptr, length * 2);
      String osAnswer = new String(buffer);
      OS.g_free(utf16Ptr);
      OS.g_free(utf8Ptr);
      answer = interpretOsAnswer(osAnswer);
    }
    OS.gtk_widget_destroy(handle);
    return answer;
  }
}
