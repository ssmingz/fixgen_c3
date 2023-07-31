class PlaceHold {
  public void moveAbove(Control control) {
    checkWidget();
    int topHandle1 = topHandle();
    if (control == null) {
      OS.PtWidgetToFront(topHandle1);
      OS.PtWindowToFront(topHandle1);
      return;
    }
    if (control.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (parent != control.parent) {
      return;
    }
    int topHandle2 = control.topHandle();
    OS.PtWidgetInsert(topHandle1, topHandle2, 0);
  }
}
