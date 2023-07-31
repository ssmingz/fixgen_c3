class PlaceHold {
  public Point getLocation() {
    checkWidget();
    if (!OS.IsWinCE) {
      if (OS.IsIconic(handle)) {
        return super.getLocation();
      }
    }
    RECT rect = new RECT();
    OS.GetWindowRect(handle, rect);
    return new Point(rect.left, rect.top);
  }
}
