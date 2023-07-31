class PlaceHold {
  void bringToTop() {
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    OS.Window_Activate(shellHandle);
  }
}
