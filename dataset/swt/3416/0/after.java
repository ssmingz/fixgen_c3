class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    switch (hdr.code) {
      case OS.RBN_HEIGHTCHANGE:
        if (ignoreResize) {
          int code = callWindowProc(RBN_HEIGHTCHANGE, wParam, lParam);
          if (code == 0) {
            return LRESULT.ZERO;
          }
          return new LRESULT(code);
        }
        Point size = getSize();
        int border = getBorderWidth();
        int height = OS.SendMessage(handle, RB_GETBARHEIGHT, 0, 0);
        setSize(size.x, height + (border * 2));
        break;
      case OS.RBN_CHEVRONPUSHED:
        NMREBARCHEVRON lpnm = new NMREBARCHEVRON();
        OS.MoveMemory(lpnm, lParam, sizeof);
        CoolItem child = items[lpnm.wID];
        if (child != null) {
          Event event = new Event();
          event.detail = SWT.ARROW;
          event.x = lpnm.left;
          event.y = lpnm.bottom;
          child.postEvent(Selection, event);
        }
        break;
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
