class PlaceHold {
  int getPosition(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int position = -1;
    if ((style & SWT.SINGLE) != 0) {
      int[] index = new int[1];
      int[] trailing = new int[1];
      int layout = OS.gtk_entry_get_layout(handle);
      OS.pango_layout_xy_to_index(
          layout, point.x * OS.PANGO_SCALE, point.y * OS.PANGO_SCALE, index, trailing);
      int ptr = OS.pango_layout_get_text(layout);
      position = ((int) (OS.g_utf16_pointer_to_offset(ptr, ptr + index[0]))) + trailing[0];
    } else {
      byte[] p = new byte[ITER_SIZEOF];
      OS.gtk_text_view_get_iter_at_location(handle, p, point.x, point.y);
      byte[] zero = new byte[ITER_SIZEOF];
      OS.gtk_text_buffer_get_iter_at_offset(bufferHandle, zero, 0);
      int ptr = OS.gtk_text_buffer_get_text(bufferHandle, zero, p, true);
      position = ((int) (OS.g_utf8_offset_to_utf16_offset(ptr, OS.gtk_text_iter_get_offset(p))));
      OS.g_free(ptr);
    }
    if (segments != null) {
      position = untranslateOffset(position);
    }
    return position;
  }
}
