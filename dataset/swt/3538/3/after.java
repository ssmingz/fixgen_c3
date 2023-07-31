class PlaceHold {
  String openClassicDialog() {
    byte[] titleBytes = Converter.wcsToMbcs(null, title, true);
    handle = OS.gtk_file_selection_new(titleBytes);
    if (parent != null) {
      int shellHandle = parent.topHandle();
      OS.gtk_window_set_transient_for(handle, shellHandle);
      int pixbufs = OS.gtk_window_get_icon_list(shellHandle);
      if (pixbufs != 0) {
        OS.gtk_window_set_icon_list(handle, pixbufs);
        OS.g_list_free(pixbufs);
      }
    }
    OS.gtk_window_set_modal(handle, true);
    presetClassicDialog();
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    display.addIdleProc();
    String answer = null;
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
    if (response == OS.GTK_RESPONSE_OK) {
      answer = computeResultClassicDialog();
    }
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    return answer;
  }
}
