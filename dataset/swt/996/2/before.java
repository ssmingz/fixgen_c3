class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    if ((style & SWT.SEPARATOR) != 0) {
      return;
    }
    int windowProc = getDisplay().windowProc;
    OS.PtAddEventHandler(handle, Ph_EV_BOUNDARY, windowProc, MouseEnter);
    OS.PtAddCallback(button, Pt_CB_ACTIVATE, windowProc, Selection);
    if ((style & SWT.DROP_DOWN) != 0) {
      OS.PtAddCallback(arrow, Pt_CB_ACTIVATE, windowProc, Selection);
    }
  }
}
