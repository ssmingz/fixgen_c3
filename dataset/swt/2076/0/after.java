class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    switch (hdr.code) {
      case OS.TBN_DROPDOWN:
        NMTOOLBAR lpnmtb = new NMTOOLBAR();
        OS.MoveMemory(lpnmtb, lParam, sizeof);
        ToolItem child = items[lpnmtb.iItem];
        if (child != null) {
          Event event = new Event();
          event.detail = SWT.ARROW;
          int index = OS.SendMessage(handle, TB_COMMANDTOINDEX, lpnmtb.iItem, 0);
          RECT rect = new RECT();
          OS.SendMessage(handle, TB_GETITEMRECT, index, rect);
          event.x = rect.left;
          event.y = rect.bottom;
          setInputState(event, Selection);
          child.postEvent(Selection, event);
          return null;
        }
        break;
      case OS.NM_CUSTOMDRAW:
        if (background == (-1)) {
          break;
        }
        NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
        OS.MoveMemory(nmcd, lParam, sizeof);
        switch (nmcd.dwDrawStage) {
          case OS.CDDS_PREERASE:
            return new LRESULT(OS.CDRF_NOTIFYPOSTERASE);
          case OS.CDDS_POSTERASE:
            drawBackground(nmcd.hdc);
            return null;
        }
        break;
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
