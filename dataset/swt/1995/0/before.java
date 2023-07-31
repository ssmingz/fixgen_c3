class PlaceHold {
  public int getCaretPosition() {
    checkWidget();
    int ptr = OS.gtk_entry_get_text(entryHandle);
    return ((int)
        (OS.g_utf8_offset_to_utf16_offset(ptr, OS.gtk_editable_get_position(entryHandle))));
  }
}
