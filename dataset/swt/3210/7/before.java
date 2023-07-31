class PlaceHold {
  public int open() {
    int parentHandle = (parent != null) ? parent.topHandle() : 0;
    int dialogFlags = OS.GTK_DIALOG_DESTROY_WITH_PARENT;
    if ((style & ((SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL)) != 0) {
      dialogFlags |= OS.GTK_DIALOG_MODAL;
    }
    int messageType = OS.GTK_MESSAGE_INFO;
    if ((style & SWT.ICON_WARNING) != 0) {
      messageType = OS.GTK_MESSAGE_WARNING;
    }
    if ((style & SWT.ICON_QUESTION) != 0) {
      messageType = OS.GTK_MESSAGE_QUESTION;
    }
    if ((style & SWT.ICON_ERROR) != 0) {
      messageType = OS.GTK_MESSAGE_ERROR;
    }
    byte[] buffer = Converter.wcsToMbcs(null, fixPercent(message), true);
    handle = OS.gtk_message_dialog_new(parentHandle, dialogFlags, messageType, 0, buffer);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (parentHandle != 0) {
      int pixbufs = OS.gtk_window_get_icon_list(parentHandle);
      if (pixbufs != 0) {
        OS.gtk_window_set_icon_list(handle, pixbufs);
        OS.g_list_free(pixbufs);
      }
    }
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    createButtons(display.getDismissalAlignment());
    buffer = Converter.wcsToMbcs(null, title, true);
    OS.gtk_window_set_title(handle, buffer);
    display.addIdleProc();
    Dialog oldModal = null;
    if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
      int group = OS.gtk_window_get_group(0);
      OS.gtk_window_group_add_window(group, handle);
    }
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
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    return response;
  }
}
