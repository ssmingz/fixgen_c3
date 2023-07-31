class PlaceHold {
  public void moveBelow(Control control) {
    checkWidget();
    int topHandle1 = topHandle();
    if (control == null) {
      OS.PtWidgetToBack(topHandle1);
      OS.PtWindowToBack(topHandle1);
      return;
    }
    if (control.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int topHandle2 = control.topHandle();
    OS.PtWidgetInsert(topHandle1, topHandle2, 1);
  }
}
