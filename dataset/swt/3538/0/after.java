class PlaceHold {
  String openChooserDialog() {
    byte[] titleBytes = Converter.wcsToMbcs(null, title, true);
    int action =
        ((style & SWT.SAVE) != 0)
            ? OS.GTK_FILE_CHOOSER_ACTION_SAVE
            : OS.GTK_FILE_CHOOSER_ACTION_OPEN;
    int shellHandle = parent.topHandle();
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    if (display.getDismissalAlignment() == SWT.RIGHT) {
      handle =
          OS.gtk_file_chooser_dialog_new(
              titleBytes,
              shellHandle,
              action,
              OS.GTK_STOCK_CANCEL(),
              GTK_RESPONSE_CANCEL,
              OS.GTK_STOCK_OK(),
              GTK_RESPONSE_OK,
              0);
    } else {
      handle =
          OS.gtk_file_chooser_dialog_new(
              titleBytes,
              shellHandle,
              action,
              OS.GTK_STOCK_OK(),
              GTK_RESPONSE_OK,
              OS.GTK_STOCK_CANCEL(),
              GTK_RESPONSE_CANCEL,
              0);
    }
    OS.gtk_window_set_modal(handle, true);
    int pixbufs = OS.gtk_window_get_icon_list(shellHandle);
    if (pixbufs != 0) {
      OS.gtk_window_set_icon_list(handle, pixbufs);
      OS.g_list_free(pixbufs);
    }
    if (uriMode) {
      OS.gtk_file_chooser_set_local_only(handle, false);
    }
    presetChooserDialog();
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
      answer = computeResultChooserDialog();
    }
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    return answer;
  }
}
