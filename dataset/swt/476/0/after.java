class PlaceHold {
  LRESULT WM_PRINTCLIENT(int wParam, int lParam) {
    LRESULT result = super.WM_PRINTCLIENT(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((state & CANVAS) != 0) {
      forceResize();
      int nSavedDC = OS.SaveDC(wParam);
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      if ((style & SWT.NO_BACKGROUND) == 0) {
        drawBackground(wParam, rect);
      }
      if (hooks(Paint) || filters(Paint)) {
        GCData data = new GCData();
        data.device = display;
        data.foreground = getForegroundPixel();
        Control control = findBackgroundControl();
        if (control == null) {
          control = this;
        }
        data.background = control.getBackgroundPixel();
        data.hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
        data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
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
      OS.RestoreDC(wParam, nSavedDC);
    }
    return result;
  }
}
