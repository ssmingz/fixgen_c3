class PlaceHold {
  void bringToTop(boolean force) {
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    if (!OS.GTK_WIDGET_VISIBLE(shellHandle)) {
      return;
    }
    Shell shell = display.getActiveShell();
    if (shell != null) {
      shell.hasFocus = false;
    }
    OS.gtk_window_present(shellHandle);
    hasFocus = true;
  }
}
