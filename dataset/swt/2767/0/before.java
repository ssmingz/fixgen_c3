class PlaceHold {
  void checkBuffered() {
    super.checkBuffered();
    if ((style & SWT.VIRTUAL) != 0) {
      style |= SWT.DOUBLE_BUFFERED;
      OS.SendMessage(handle, TVM_SETSCROLLTIME, 0, 0);
    }
    if (EXPLORER_THEME) {
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
        int exStyle = ((int) (OS.SendMessage(handle, TVM_GETEXTENDEDSTYLE, 0, 0)));
        if ((exStyle & OS.TVS_EX_DOUBLEBUFFER) != 0) {
          style |= SWT.DOUBLE_BUFFERED;
        }
      }
    }
  }
}
