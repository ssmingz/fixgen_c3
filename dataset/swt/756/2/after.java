class PlaceHold {
  public Point getSelection() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      int length = 0;
      int index = OS.gtk_combo_box_get_active(handle);
      if (index != (-1)) {
        length = getItem(index).length();
      }
      return new Point(0, length);
    }
    int[] start = new int[1];
    int[] end = new int[1];
    if (entryHandle != 0) {
      OS.gtk_editable_get_selection_bounds(entryHandle, start, end);
      long ptr = OS.gtk_entry_get_text(entryHandle);
      start[0] = ((int) (OS.g_utf8_offset_to_utf16_offset(ptr, start[0])));
      end[0] = ((int) (OS.g_utf8_offset_to_utf16_offset(ptr, end[0])));
    }
    return new Point(start[0], end[0]);
  }
}
