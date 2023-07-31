class PlaceHold {
  public void setMessage(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    message = string;
    if ((style & SWT.BALLOON) == 0) {
      return;
    }
    if (layoutMessage != 0) {
      OS.g_object_unref(layoutMessage);
    }
    layoutMessage = 0;
    if (message.length() != 0) {
      byte[] buffer = Converter.wcsToMbcs(null, message, true);
      layoutMessage = OS.gtk_widget_create_pango_layout(handle, buffer);
      if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
        OS.pango_layout_set_auto_dir(layoutMessage, false);
      }
      OS.pango_layout_set_wrap(layoutMessage, PANGO_WRAP_WORD_CHAR);
    }
    if (gtk_widget_get_visible(handle)) {
      configure();
    }
  }
}
