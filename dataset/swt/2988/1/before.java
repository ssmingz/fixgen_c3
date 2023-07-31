class PlaceHold {
  public int getCharCount() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      int ptr = OS.gtk_entry_get_text(handle);
      return OS.g_utf8_strlen(ptr, -1);
    }
    return OS.gtk_text_buffer_get_char_count(bufferHandle);
  }
}
