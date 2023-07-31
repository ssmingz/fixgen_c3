class PlaceHold {
  public void setSelection(Point selection) {
    checkWidget();
    if (selection == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    if (entryHandle != 0) {
      int ptr = OS.gtk_entry_get_text(entryHandle);
      int start = ((int) (OS.g_utf16_offset_to_utf8_offset(ptr, selection.x)));
      int end = ((int) (OS.g_utf16_offset_to_utf8_offset(ptr, selection.y)));
      OS.gtk_editable_set_position(entryHandle, start);
      OS.gtk_editable_select_region(entryHandle, start, end);
    }
  }
}
