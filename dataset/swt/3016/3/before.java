class PlaceHold {
  public RGB open() {
    byte[] buffer = Converter.wcsToMbcs(null, title, true);
    int handle = OS.gtk_color_selection_dialog_new(buffer);
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    if (parent != null) {
      int shellHandle = parent.topHandle();
      OS.gtk_window_set_transient_for(handle, shellHandle);
      int pixbufs = OS.gtk_window_get_icon_list(shellHandle);
      if (pixbufs != 0) {
        OS.gtk_window_set_icon_list(handle, pixbufs);
        OS.g_list_free(pixbufs);
      }
    }
    if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
      int group = OS.gtk_window_get_group(0);
      OS.gtk_window_group_add_window(group, handle);
    }
    OS.gtk_window_set_modal(handle, true);
    GtkColorSelectionDialog dialog = new GtkColorSelectionDialog();
    OS.memmove(dialog, handle);
    GdkColor color = new GdkColor();
    if (rgb != null) {
      color.red = ((short) ((rgb.red & 0xff) | ((rgb.red & 0xff) << 8)));
      color.green = ((short) ((rgb.green & 0xff) | ((rgb.green & 0xff) << 8)));
      color.blue = ((short) ((rgb.blue & 0xff) | ((rgb.blue & 0xff) << 8)));
      OS.gtk_color_selection_set_current_color(dialog.colorsel, color);
    }
    OS.gtk_color_selection_set_has_palette(dialog.colorsel, true);
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
      OS.gtk_color_selection_get_current_color(dialog.colorsel, color);
      int red = (color.red >> 8) & 0xff;
      int green = (color.green >> 8) & 0xff;
      int blue = (color.blue >> 8) & 0xff;
      rgb = new RGB(red, green, blue);
    }
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    if (!success) {
      return null;
    }
    return rgb;
  }
}
