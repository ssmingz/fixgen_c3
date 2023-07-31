class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string);
    if ((style & SWT.SINGLE) != 0) {
      OS.gtk_editable_delete_text(handle, 0, -1);
      int[] position = new int[1];
      OS.gtk_editable_insert_text(handle, buffer, buffer.length, position);
      OS.gtk_editable_set_position(handle, 0);
    } else {
      byte[] position = new byte[ITER_SIZEOF];
      OS.gtk_text_buffer_set_text(bufferHandle, buffer, buffer.length);
      OS.gtk_text_buffer_get_iter_at_offset(bufferHandle, position, 0);
      OS.gtk_text_buffer_place_cursor(bufferHandle, position);
    }
  }
}
