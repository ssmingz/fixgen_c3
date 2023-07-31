class PlaceHold {
  public void paste() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      OS.gtk_editable_paste_clipboard(handle);
    } else {
      long clipboard = OS.gtk_clipboard_get(GDK_NONE);
      clearSegments(true);
      OS.gtk_text_buffer_paste_clipboard(
          bufferHandle, clipboard, null, OS.gtk_text_view_get_editable(handle));
      applySegments();
    }
  }
}
