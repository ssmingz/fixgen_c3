class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    switch (hdr.code) {
      case OS.RBN_HEIGHTCHANGE:
        if (!ignoreResize) {
          Point size = getSize();
          int border = getBorderWidth();
          int height = OS.SendMessage(handle, RB_GETBARHEIGHT, 0, 0);
          setSize(size.x, height + (border * 2));
        }
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
      case OS.NM_CUSTOMDRAW:
        if (OS.COMCTL32_MAJOR < 6) {
          break;
        }
        if (background != (-1)) {
          NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
          OS.MoveMemory(nmcd, lParam, sizeof);
          switch (nmcd.dwDrawStage) {
            case OS.CDDS_PREERASE:
              return new LRESULT(OS.CDRF_NOTIFYPOSTERASE);
            case OS.CDDS_POSTERASE:
              drawBackground(nmcd.hdc);
              break;
          }
        }
        break;
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
