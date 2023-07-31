class PlaceHold {
  void redrawWidget(int control, boolean children) {
    if (!isDrawing(control)) {
      return;
    }
    int window = OS.GetControlOwner(control);
    int visibleRgn = getVisibleRegion(control, !children);
    invalWindowRgn(window, visibleRgn);
    OS.DisposeRgn(visibleRgn);
  }
}
