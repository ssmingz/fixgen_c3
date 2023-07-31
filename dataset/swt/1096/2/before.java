class PlaceHold {
  public void moveAbove(Control control) {
    checkWidget();
    int topHandle1 = topHandle();
    if (control == null) {
      OS.PtWidgetToFront(topHandle1);
      OS.PtWindowToFront(topHandle1);
      return;
    }
    int topHandle2 = control.topHandle();
    OS.PtWidgetInsert(topHandle1, topHandle2, 0);
  }
}
