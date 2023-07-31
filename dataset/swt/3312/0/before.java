class PlaceHold {
  void redrawWidget(int control, int x, int y, int width, int height, boolean children) {
    if (!isDrawing(control)) {
      return;
    }
    Rect rect = new Rect();
    OS.GetControlBounds(control, rect);
    x += rect.left;
    y += rect.top;
    OS.SetRect(rect, ((short) (x)), ((short) (y)), ((short) (x + width)), ((short) (y + height)));
    int rectRgn = OS.NewRgn();
    OS.RectRgn(rectRgn, rect);
    int visibleRgn = getVisibleRegion(control, !children);
    OS.SectRgn(rectRgn, visibleRgn, visibleRgn);
    int window = OS.GetControlOwner(control);
    OS.InvalWindowRgn(window, visibleRgn);
    OS.DisposeRgn(rectRgn);
    OS.DisposeRgn(visibleRgn);
  }
}
