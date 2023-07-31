class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
    int hwndTrack = parent.handle;
    POINT pt = new POINT();
    pt.x = ((short) (lParam & 0xffff));
    pt.y = ((short) (lParam >> 16));
    RECT rect = new RECT();
    OS.GetWindowRect(handle, rect);
    OS.MapWindowPoints(handle, 0, pt, 1);
    startX = pt.x - rect.left;
    startY = pt.y - rect.top;
    OS.MapWindowPoints(0, hwndTrack, rect, 2);
    lastX = rect.left;
    lastY = rect.top;
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    Event event = new Event();
    event.x = lastX;
    event.y = lastY;
    event.width = width;
    event.height = height;
    event.detail = SWT.DRAG;
    sendEvent(Selection, event);
    if (isDisposed()) {
      return LRESULT.ZERO;
    }
    if (event.doit) {
      dragging = true;
      menuShell().bringToTop();
      if (OS.IsWinCE) {
        OS.UpdateWindow(hwndTrack);
      } else {
        int flags = OS.RDW_UPDATENOW | OS.RDW_ALLCHILDREN;
        OS.RedrawWindow(hwndTrack, null, 0, flags);
      }
      drawBand(lastX = event.x, lastY = event.y, width, height);
    }
    return result;
  }
}
