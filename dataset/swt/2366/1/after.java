class PlaceHold {
  public int getCaretLineNumber() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return 0;
    }
    byte[] position = new byte[ITER_SIZEOF];
    long mark = OS.gtk_text_buffer_get_insert(bufferHandle);
    OS.gtk_text_buffer_get_iter_at_mark(bufferHandle, position, mark);
    return OS.gtk_text_iter_get_line(position);
  }
}
