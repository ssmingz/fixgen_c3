class PlaceHold {
  String openClassicDialog() {
    byte[] titleBytes = Converter.wcsToMbcs(null, title, true);
    int handle = OS.gtk_file_selection_new(titleBytes);
    if (parent != null) {
      int shellHandle = parent.topHandle();
      OS.gtk_window_set_transient_for(handle, shellHandle);
      int pixbufs = OS.gtk_window_get_icon_list(shellHandle);
      if (pixbufs != 0) {
        OS.gtk_window_set_icon_list(handle, pixbufs);
        OS.g_list_free(pixbufs);
      }
    }
    String answer = null;
    if (filterPath != null) {
      String path = filterPath;
      if ((path.length() > 0) && (!path.endsWith(SEPARATOR))) {
        path += SEPARATOR;
      }
      int length = path.length();
      char[] buffer = new char[length + 1];
      path.getChars(0, length, buffer, 0);
      int utf8Ptr = OS.g_utf16_to_utf8(buffer, -1, null, null, null);
      int fileNamePtr = OS.g_filename_from_utf8(utf8Ptr, -1, null, null, null);
      OS.gtk_file_selection_set_filename(handle, fileNamePtr);
      OS.g_free(utf8Ptr);
      OS.g_free(fileNamePtr);
    }
    GtkFileSelection selection = new GtkFileSelection();
    OS.memmove(selection, handle);
    OS.gtk_file_selection_hide_fileop_buttons(handle);
    int fileListParent = OS.gtk_widget_get_parent(selection.file_list);
    OS.gtk_widget_hide(selection.file_list);
    OS.gtk_widget_hide(fileListParent);
    if (message.length() > 0) {
      byte[] buffer = Converter.wcsToMbcs(null, message, true);
      int labelHandle = OS.gtk_label_new(buffer);
      OS.gtk_label_set_line_wrap(labelHandle, true);
      OS.gtk_misc_set_alignment(labelHandle, 0.0F, 0.0F);
      OS.gtk_container_add(selection.main_vbox, labelHandle);
      OS.gtk_box_set_child_packing(
          selection.main_vbox, labelHandle, false, false, 0, GTK_PACK_START);
      OS.gtk_widget_show(labelHandle);
    }
    Display display = (parent != null) ? parent.getDisplay() : Display.getCurrent();
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
      int fileNamePtr = OS.gtk_file_selection_get_filename(handle);
      int utf8Ptr = OS.g_filename_to_utf8(fileNamePtr, -1, null, null, null);
      if (utf8Ptr != 0) {
        int[] items_written = new int[1];
        int utf16Ptr = OS.g_utf8_to_utf16(utf8Ptr, -1, null, items_written, null);
        if (utf16Ptr != 0) {
          int length = ((int) (items_written[0]));
          char[] buffer = new char[length];
          OS.memmove(buffer, utf16Ptr, length * 2);
          String osAnswer = new String(buffer);
          if (osAnswer != null) {
            if ((!osAnswer.equals(SEPARATOR)) && osAnswer.endsWith(SEPARATOR)) {
              osAnswer = osAnswer.substring(0, osAnswer.length() - 1);
            }
            answer = filterPath = osAnswer;
          }
          OS.g_free(utf16Ptr);
        }
        OS.g_free(utf8Ptr);
      }
    }
    display.removeIdleProc();
    OS.gtk_widget_destroy(handle);
    return answer;
  }
}
