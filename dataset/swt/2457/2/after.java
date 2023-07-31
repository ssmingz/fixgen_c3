class PlaceHold {
  void updateModal() {
    if (OS.GTK_IS_PLUG(shellHandle)) {
      return;
    }
    long group = 0;
    boolean isModalShell = false;
    if (display.getModalDialog() == null) {
      Shell modal = getModalShell();
      int mask = (SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL;
      Composite shell = null;
      if (modal == null) {
        if ((style & mask) != 0) {
          shell = this;
          isModalShell = OS.gtk_window_get_modal(shellHandle);
          if (isModalShell) {
            OS.gtk_window_set_modal(shellHandle, false);
          }
        }
      } else {
        shell = modal;
      }
      Composite topModalShell = shell;
      while (shell != null) {
        if ((shell.style & mask) == 0) {
          group = shell.getShell().group;
          break;
        }
        topModalShell = shell;
        shell = shell.parent;
      }
      if ((group == 0) && (topModalShell != null)) {
        group = topModalShell.getShell().group;
      }
    }
    if ((OS.GTK_VERSION >= OS.VERSION(2, 10, 0)) && (group == 0)) {
      group = OS.gtk_window_get_group(0);
    }
    if (group != 0) {
      OS.gtk_window_group_add_window(group, shellHandle);
      if (isModalShell) {
        OS.gtk_window_set_modal(shellHandle, true);
      }
    } else if (modalGroup != 0) {
      OS.gtk_window_group_remove_window(modalGroup, shellHandle);
    }
    modalGroup = group;
  }
}
