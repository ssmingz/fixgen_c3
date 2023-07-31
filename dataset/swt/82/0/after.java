class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    fixIM();
    if (OS.GTK_VERSION < OS.VERSION(2, 6, 0)) {
      if ((style & SWT.MULTI) != 0) {
        long clipboard = OS.gtk_clipboard_get(GDK_NONE);
        OS.gtk_text_buffer_paste_clipboard(
            bufferHandle, clipboard, null, OS.gtk_text_view_get_editable(handle));
      }
    }
    message = null;
  }
}
