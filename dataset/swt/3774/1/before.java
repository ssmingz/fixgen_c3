class PlaceHold {
  public void setToolTipText(String string) {
    checkWidget();
    toolTipText = string;
    byte[] buffer = null;
    if ((string != null) && (string.length() > 0)) {
      buffer = Converter.wcsToMbcs(null, string, true);
    }
    if (tooltipsHandle == 0) {
      tooltipsHandle = OS.gtk_tooltips_new();
      if (tooltipsHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.g_object_ref(tooltipsHandle);
      OS.gtk_object_sink(tooltipsHandle);
    }
    if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
      OS.gtk_status_icon_set_tooltip(handle, buffer);
    } else {
      OS.gtk_tooltips_set_tip(tooltipsHandle, handle, buffer, null);
    }
  }
}
