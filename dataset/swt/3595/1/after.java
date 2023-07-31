class PlaceHold {
  LRESULT wmScrollChild(int wParam, int lParam) {
    int code = wParam & 0xffff;
    switch (code) {
      case OS.SB_THUMBPOSITION:
      case OS.SB_ENDSCROLL:
        return null;
    }
    Event event = new Event();
    switch (code) {
      case OS.SB_THUMBTRACK:
        event.detail = SWT.DRAG;
        break;
      case OS.SB_TOP:
        event.detail = SWT.HOME;
        break;
      case OS.SB_BOTTOM:
        event.detail = SWT.END;
        break;
      case OS.SB_LINEDOWN:
        event.detail = SWT.ARROW_DOWN;
        break;
      case OS.SB_LINEUP:
        event.detail = SWT.ARROW_UP;
        break;
      case OS.SB_PAGEDOWN:
        event.detail = SWT.PAGE_DOWN;
        break;
      case OS.SB_PAGEUP:
        event.detail = SWT.PAGE_UP;
        break;
    }
    sendEvent(Selection, event);
    return null;
  }
}
