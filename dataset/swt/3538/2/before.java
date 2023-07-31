class PlaceHold {
  public FontData open() {
    int handle;
    byte[] titleBytes;
    titleBytes = Converter.wcsToMbcs(null, title, true);
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    handle = OS.gtk_font_selection_dialog_new(titleBytes);
    if (parent != null) {
      int shellHandle = parent.topHandle();
      OS.gtk_window_set_transient_for(handle, shellHandle);
      int pixbufs = OS.gtk_window_get_icon_list(shellHandle);
      if (pixbufs != 0) {
        OS.gtk_window_set_icon_list(handle, pixbufs);
        OS.g_list_free(pixbufs);
      }
    }
    if (fontData != null) {
      Font font = new Font(display, fontData);
      int fontName = OS.pango_font_description_to_string(font.handle);
      int length = OS.strlen(fontName);
      byte[] buffer = new byte[length + 1];
      OS.memmove(buffer, fontName, length);
      font.dispose();
      OS.g_free(fontName);
      OS.gtk_font_selection_dialog_set_font_name(handle, buffer);
    }
    display.addIdleProc();
    Dialog oldModal = null;
    if (OS.gtk_window_get_modal(handle)) {
      oldModal = display.getModalDialog();
      display.setModalDialog(this);
    }
    int signalId = 0;
    int hookId = 0;
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      signalId = OS.g_signal_lookup(map, OS.GTK_TYPE_WIDGET());
      hookId = OS.g_signal_add_emission_hook(signalId, 0, display.emissionProc, handle, 0);
    }
    int response = OS.gtk_dialog_run(handle);
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      OS.g_signal_remove_emission_hook(signalId, hookId);
    }
    if (OS.gtk_window_get_modal(handle)) {
      display.setModalDialog(oldModal);
    }
    boolean success = response == OS.GTK_RESPONSE_OK;
    if (success) {
      int fontName = OS.gtk_font_selection_dialog_get_font_name(handle);
      int length = OS.strlen(fontName);
      byte[] buffer = new byte[length + 1];
      OS.memmove(buffer, fontName, length);
      OS.g_free(fontName);
      int fontDesc = OS.pango_font_description_from_string(buffer);
      Font font = Font.gtk_new(display, fontDesc);
      fontData = font.getFontData()[0];
      OS.pango_font_description_free(fontDesc);
    }
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    if (!success) {
      return null;
    }
    return fontData;
  }
}
