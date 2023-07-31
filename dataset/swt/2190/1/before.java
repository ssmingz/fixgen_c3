class PlaceHold {
  public FontData open() {
    int handle;
    byte[] titleBytes;
    titleBytes = Converter.wcsToMbcs(null, title, true);
    handle = OS.gtk_font_selection_dialog_new(titleBytes);
    if (parent != null) {
      OS.gtk_window_set_modal(handle, true);
      OS.gtk_window_set_transient_for(handle, parent.topHandle());
    }
    if (fontData != null) {
      Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
      Font font = new Font(display, fontData);
      int fontName = OS.pango_font_description_to_string(font.handle);
      int length = OS.strlen(fontName);
      byte[] buffer = new byte[length];
      OS.memmove(buffer, fontName, length);
      font.dispose();
      OS.g_free(fontName);
      OS.gtk_font_selection_dialog_set_font_name(handle, buffer);
    }
    Callback destroyCallback = new Callback(this, "destroyFunc", 2);
    int destroyFunc = destroyCallback.getAddress();
    byte[] destroy = Converter.wcsToMbcs(null, "destroy", true);
    OS.gtk_signal_connect(handle, destroy, destroyFunc, handle);
    byte[] clicked = Converter.wcsToMbcs(null, "clicked", true);
    Callback okCallback = new Callback(this, "okFunc", 2);
    int okFunc = okCallback.getAddress();
    Callback cancelCallback = new Callback(this, "cancelFunc", 2);
    int cancelFunc = cancelCallback.getAddress();
    OS.gtk_signal_connect(OS.GTK_FONT_SELECTION_DIALOG_OK_BUTTON(handle), clicked, okFunc, handle);
    OS.gtk_signal_connect(
        OS.GTK_FONT_SELECTION_DIALOG_CANCEL_BUTTON(handle), clicked, cancelFunc, handle);
    fontData = null;
    OS.gtk_widget_show_now(handle);
    OS.gtk_main();
    destroyCallback.dispose();
    okCallback.dispose();
    cancelCallback.dispose();
    return fontData;
  }
}
