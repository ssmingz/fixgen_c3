class PlaceHold {
  LRESULT wmNotifyChild(NMHDR hdr, int wParam, int lParam) {
    switch (hdr.code) {
      case OS.RBN_BEGINDRAG:
        {
          int pos = OS.GetMessagePos();
          POINT pt = new POINT();
          OS.POINTSTOPOINT(pt, pos);
          OS.ScreenToClient(handle, pt);
          int button = (display.lastButton != 0) ? display.lastButton : 1;
          if (!sendDragEvent(button, pt.x, pt.y)) {
            return LRESULT.ONE;
          }
          break;
        }
      case OS.RBN_CHILDSIZE:
        {
          NMREBARCHILDSIZE lprbcs = new NMREBARCHILDSIZE();
          OS.MoveMemory(lprbcs, lParam, sizeof);
          if (lprbcs.uBand != (-1)) {
            CoolItem item = items[lprbcs.wID];
            Control control = item.control;
            if (control != null) {
              int width = lprbcs.rcChild_right - lprbcs.rcChild_left;
              int height = lprbcs.rcChild_bottom - lprbcs.rcChild_top;
              control.setBounds(lprbcs.rcChild_left, lprbcs.rcChild_top, width, height);
            }
          }
          break;
        }
      case OS.RBN_HEIGHTCHANGE:
        {
          if (!ignoreResize) {
            Point size = getSize();
            int border = getBorderWidth();
            int barHeight = ((int) (OS.SendMessage(handle, RB_GETBARHEIGHT, 0, 0)));
            if ((style & SWT.VERTICAL) != 0) {
              setSize(barHeight + (2 * border), size.y);
            } else {
              setSize(size.x, barHeight + (2 * border));
            }
          }
          break;
        }
      case OS.RBN_CHEVRONPUSHED:
        {
          NMREBARCHEVRON lpnm = new NMREBARCHEVRON();
          OS.MoveMemory(lpnm, lParam, sizeof);
          CoolItem item = items[lpnm.wID];
          if (item != null) {
            Event event = new Event();
            event.detail = SWT.ARROW;
            if ((style & SWT.VERTICAL) != 0) {
              event.x = lpnm.right;
              event.y = lpnm.top;
            } else {
              event.x = lpnm.left;
              event.y = lpnm.bottom;
            }
            item.postEvent(Selection, event);
          }
          break;
        }
      case OS.NM_CUSTOMDRAW:
        {
          if (OS.COMCTL32_MAJOR < 6) {
            break;
          }
          if ((findBackgroundControl() != null) || ((style & SWT.FLAT) != 0)) {
            NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
            OS.MoveMemory(nmcd, lParam, sizeof);
            switch (nmcd.dwDrawStage) {
              case OS.CDDS_PREERASE:
                return new LRESULT(OS.CDRF_SKIPDEFAULT | OS.CDRF_NOTIFYPOSTERASE);
              case OS.CDDS_POSTERASE:
                drawBackground(nmcd.hdc);
                break;
            }
          }
          break;
        }
    }
    return super.wmNotifyChild(hdr, wParam, lParam);
  }
}
