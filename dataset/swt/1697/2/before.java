class PlaceHold {
  LRESULT wmScrollChild(int wParam, int lParam) {
    int code = wParam & 0xffff;
    if (code == OS.SB_ENDSCROLL) {
      return null;
    }
    Event event = new Event();
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = (OS.SIF_TRACKPOS | OS.SIF_POS) | OS.SIF_RANGE;
    OS.GetScrollInfo(handle, SB_CTL, info);
    info.fMask = OS.SIF_POS;
    switch (code) {
      case OS.SB_THUMBPOSITION:
        event.detail = SWT.NONE;
        info.nPos = info.nTrackPos;
        break;
      case OS.SB_THUMBTRACK:
        event.detail = SWT.DRAG;
        info.nPos = info.nTrackPos;
        break;
      case OS.SB_TOP:
        event.detail = SWT.HOME;
        info.nPos = info.nMin;
        break;
      case OS.SB_BOTTOM:
        event.detail = SWT.END;
        info.nPos = info.nMax;
        break;
      case OS.SB_LINEDOWN:
        event.detail = SWT.ARROW_DOWN;
        info.nPos += increment;
        break;
      case OS.SB_LINEUP:
        event.detail = SWT.ARROW_UP;
        info.nPos = Math.max(info.nMin, info.nPos - increment);
        break;
      case OS.SB_PAGEDOWN:
        event.detail = SWT.PAGE_DOWN;
        info.nPos += pageIncrement;
        break;
      case OS.SB_PAGEUP:
        event.detail = SWT.PAGE_UP;
        info.nPos = Math.max(info.nMin, info.nPos - pageIncrement);
        break;
    }
    OS.SetScrollInfo(handle, SB_CTL, info, true);
    sendEvent(Selection, event);
    return null;
  }
}
