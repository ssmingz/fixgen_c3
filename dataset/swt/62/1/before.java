class PlaceHold {
  void setPlacement(int x, int y, int width, int height, int flags) {
    WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
    lpwndpl.length = WINDOWPLACEMENT.sizeof;
    OS.GetWindowPlacement(handle, lpwndpl);
    lpwndpl.showCmd = OS.SW_SHOWNA;
    if (OS.IsIconic(handle)) {
      lpwndpl.showCmd = OS.SW_SHOWMINNOACTIVE;
    } else if (OS.IsZoomed(handle)) {
      lpwndpl.showCmd = OS.SW_SHOWMAXIMIZED;
    }
    boolean sameOrigin = true;
    if ((flags & OS.SWP_NOMOVE) == 0) {
      sameOrigin = (lpwndpl.left != x) || (lpwndpl.top != y);
      lpwndpl.right = x + (lpwndpl.right - lpwndpl.left);
      lpwndpl.bottom = y + (lpwndpl.bottom - lpwndpl.top);
      lpwndpl.left = x;
      lpwndpl.top = y;
    }
    boolean sameExtent = true;
    if ((flags & OS.SWP_NOSIZE) == 0) {
      sameExtent =
          ((lpwndpl.right - lpwndpl.left) != width) || ((lpwndpl.bottom - lpwndpl.top) != height);
      lpwndpl.right = lpwndpl.left + width;
      lpwndpl.bottom = lpwndpl.top + height;
    }
    OS.SetWindowPlacement(handle, lpwndpl);
    if (OS.IsIconic(handle)) {
      if (sameOrigin) {
        moved = true;
        Point location = getLocation();
        oldX = location.x;
        oldY = location.y;
        sendEvent(Move);
        if (isDisposed()) {
          return;
        }
      }
      if (sameExtent) {
        resized = true;
        Rectangle rect = getClientArea();
        oldWidth = rect.width;
        oldHeight = rect.height;
        sendEvent(Resize);
        if (isDisposed()) {
          return;
        }
        if (layout != null) {
          layout.layout(this, false);
        }
      }
    }
  }
}
