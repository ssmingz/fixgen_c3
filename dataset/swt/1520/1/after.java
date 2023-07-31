class PlaceHold {
  LRESULT wmScroll(ScrollBar bar, int msg, int wParam, int lParam) {
    LRESULT result = null;
    if ((state & CANVAS) != 0) {
      int type = (msg == OS.WM_HSCROLL) ? OS.SB_HORZ : OS.SB_VERT;
      SCROLLINFO info = new SCROLLINFO();
      info.cbSize = SCROLLINFO.sizeof;
      info.fMask = (OS.SIF_TRACKPOS | OS.SIF_POS) | OS.SIF_RANGE;
      OS.GetScrollInfo(handle, type, info);
      info.fMask = OS.SIF_POS;
      int code = wParam & 0xffff;
      switch (code) {
        case OS.SB_ENDSCROLL:
          return null;
        case OS.SB_THUMBTRACK:
        case OS.SB_THUMBPOSITION:
          info.nPos = info.nTrackPos;
          break;
        case OS.SB_TOP:
          info.nPos = info.nMin;
          break;
        case OS.SB_BOTTOM:
          info.nPos = info.nMax;
          break;
        case OS.SB_LINEDOWN:
          info.nPos += bar.getIncrement();
          break;
        case OS.SB_LINEUP:
          int increment = bar.getIncrement();
          info.nPos = Math.max(info.nMin, info.nPos - increment);
          break;
        case OS.SB_PAGEDOWN:
          info.nPos += bar.getPageIncrement();
          break;
        case OS.SB_PAGEUP:
          int pageIncrement = bar.getPageIncrement();
          info.nPos = Math.max(info.nMin, info.nPos - pageIncrement);
          break;
      }
      OS.SetScrollInfo(handle, type, info, true);
    } else {
      int code = callWindowProc(handle, msg, wParam, lParam);
      result = (code == 0) ? LRESULT.ZERO : new LRESULT(code);
    }
    bar.wmScrollChild(wParam, lParam);
    return result;
  }
}
