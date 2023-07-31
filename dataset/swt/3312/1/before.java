class PlaceHold {
  void setVisible(int control, boolean visible) {
    int visibleRgn = 0;
    boolean drawing = getDrawCount(control) == 0;
    if (drawing && (!visible)) {
      visibleRgn = getVisibleRegion(control, false);
    }
    OS.SetControlVisibility(control, visible, false);
    invalidateVisibleRegion(control);
    if (drawing && visible) {
      visibleRgn = getVisibleRegion(control, false);
    }
    if (drawing) {
      int window = OS.GetControlOwner(control);
      OS.InvalWindowRgn(window, visibleRgn);
      OS.DisposeRgn(visibleRgn);
    }
  }
}
