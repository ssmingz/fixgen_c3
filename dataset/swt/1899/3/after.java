class PlaceHold {
  LRESULT WM_PRINTCLIENT(int wParam, int lParam) {
    LRESULT result = super.WM_PRINTCLIENT(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((state & CANVAS) != 0) {
      forceResize();
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      if ((style & SWT.NO_BACKGROUND) == 0) {
        drawBackground(wParam, rect);
      }
      GCData data = new GCData();
      data.device = display;
      GC gc = GC.win32_new(wParam, data);
      Event event = new Event();
      event.gc = gc;
      event.x = rect.left;
      event.y = rect.top;
      event.width = rect.right - rect.left;
      event.height = rect.bottom - rect.top;
      sendEvent(Paint, event);
      event.gc = null;
      gc.dispose();
    }
    return result;
  }
}
