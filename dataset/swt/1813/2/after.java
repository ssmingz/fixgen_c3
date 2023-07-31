class PlaceHold {
  String openChooserDialog() {
    byte[] titleBytes = Converter.wcsToMbcs(null, title, true);
    int shellHandle = parent.topHandle();
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
    int handle = 0;
    if (display.getDismissalAlignment() == SWT.RIGHT) {
      handle =
          OS.gtk_file_chooser_dialog_new(
              titleBytes,
              shellHandle,
              GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER,
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
              GTK_FILE_CHOOSER_ACTION_SELECT_FOLDER,
              OS.GTK_STOCK_OK(),
              GTK_RESPONSE_OK,
              OS.GTK_STOCK_CANCEL(),
              GTK_RESPONSE_CANCEL,
              0);
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) {
      int group = OS.gtk_window_get_group(0);
      OS.gtk_window_group_add_window(group, handle);
    }
    OS.gtk_window_set_modal(handle, true);
    int pixbufs = OS.gtk_window_get_icon_list(shellHandle);
    if (pixbufs != 0) {
      OS.gtk_window_set_icon_list(handle, pixbufs);
      OS.g_list_free(pixbufs);
    }
    if ((filterPath != null) && (filterPath.length() > 0)) {
      StringBuffer stringBuffer = new StringBuffer();
      if (!filterPath.startsWith(SEPARATOR)) {
        stringBuffer.append(SEPARATOR);
      }
      stringBuffer.append(filterPath);
      byte[] buffer = Converter.wcsToMbcs(null, stringBuffer.toString(), true);
      int ptr = OS.realpath(buffer, null);
      if (ptr != 0) {
        OS.gtk_file_chooser_set_current_folder(handle, ptr);
        OS.g_free(ptr);
      }
    }
    if (message.length() > 0) {
      byte[] buffer = Converter.wcsToMbcs(null, message, true);
      int box = OS.gtk_hbox_new(false, 0);
      if (box == 0) {
        error(ERROR_NO_HANDLES);
      }
      int label = OS.gtk_label_new(buffer);
      if (label == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_container_add(box, label);
      OS.gtk_widget_show(label);
      OS.gtk_label_set_line_wrap(label, true);
      OS.gtk_label_set_justify(label, GTK_JUSTIFY_CENTER);
      OS.gtk_file_chooser_set_extra_widget(handle, box);
    }
    String answer = null;
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
    if (response == OS.GTK_RESPONSE_OK) {
      int path = OS.gtk_file_chooser_get_filename(handle);
      if (path != 0) {
        int utf8Ptr = OS.g_filename_to_utf8(path, -1, null, null, null);
        OS.g_free(path);
        if (utf8Ptr != 0) {
          int[] items_written = new int[1];
          int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
          OS.g_free(utf8Ptr);
          if (utf16Ptr != 0) {
            int clength = ((int) (items_written[0]));
            char[] chars = new char[clength];
            OS.memmove(chars, utf16Ptr, clength * 2);
            OS.g_free(utf16Ptr);
            answer = new String(chars);
            filterPath = answer;
          }
        }
      }
    }
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    return answer;
  }
}
