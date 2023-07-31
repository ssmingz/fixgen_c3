class PlaceHold {
  public void redraw(int x, int y, int width, int height, boolean all) {
    checkWidget();
    if (getDrawCount() > 0) {
      return;
    }
    if (!OS.IsControlVisible(handle)) {
      return;
    }
    Rect rect = new Rect();
    OS.GetControlBounds(handle, rect);
    x += rect.left;
    y += rect.top;
    OS.SetRect(rect, ((short) (x)), ((short) (y)), ((short) (x + width)), ((short) (y + height)));
    int window = OS.GetControlOwner(handle);
    OS.InvalWindowRect(window, rect);
  }
}
