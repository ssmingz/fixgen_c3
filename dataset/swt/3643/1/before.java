class PlaceHold {
  void setDefault(boolean value) {
    if ((style & SWT.PUSH) == 0) {
      return;
    }
    int hwndShell = menuShell().handle;
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if (value) {
      bits |= OS.BS_DEFPUSHBUTTON;
      OS.SendMessage(hwndShell, DM_SETDEFID, handle, 0);
    } else {
      bits &= ~OS.BS_DEFPUSHBUTTON;
      OS.SendMessage(hwndShell, DM_SETDEFID, 0, 0);
    }
    OS.SendMessage(handle, BM_SETSTYLE, bits, 1);
  }
}
