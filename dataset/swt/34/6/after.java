class PlaceHold {
  void addWidget() {
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    if (parent != null) {
      OS.Window_Owner(shellHandle, ((Shell) (parent)).shellHandle);
    }
  }
}
