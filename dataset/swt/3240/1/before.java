class PlaceHold {
  public void cut() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      OS.gtk_editable_cut_clipboard(handle);
    } else {
      int clipboard = OS.gtk_clipboard_get(GDK_NONE);
      if (hooks(GetSegments) || filters(GetSegments)) {
        if (segments != null) {
          clearSegments(true);
        }
        OS.gtk_text_buffer_cut_clipboard(
            bufferHandle, clipboard, OS.gtk_text_view_get_editable(handle));
        applySegments();
      } else {
        OS.gtk_text_buffer_cut_clipboard(
            bufferHandle, clipboard, OS.gtk_text_view_get_editable(handle));
      }
    }
  }
}
