class PlaceHold {
  public int getCaretPosition() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return 0;
    }
    long ptr = OS.gtk_entry_get_text(entryHandle);
    return ((int)
        (OS.g_utf8_offset_to_utf16_offset(ptr, OS.gtk_editable_get_position(entryHandle))));
  }
}
