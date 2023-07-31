class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    int code = hdr.code;
    switch (code) {
      case OS.NM_CUSTOMDRAW:
        {
          if (!customDraw) {
            break;
          }
          NMTVCUSTOMDRAW nmcd = new NMTVCUSTOMDRAW();
          OS.MoveMemory(nmcd, lParam, sizeof);
          switch (nmcd.dwDrawStage) {
            case OS.CDDS_PREPAINT:
              return new LRESULT(OS.CDRF_NOTIFYITEMDRAW);
            case OS.CDDS_ITEMPREPAINT:
              TreeItem item = items[nmcd.lItemlParam];
              TVITEM tvItem = new TVITEM();
              tvItem.mask = OS.TVIF_STATE;
              tvItem.hItem = item.handle;
              OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
              if ((tvItem.state & OS.TVIS_SELECTED) != 0) {
                break;
              }
              int clrText = item.foreground;
              int clrTextBk = item.background;
              if ((clrText == (-1)) && (clrTextBk == (-1))) {
                break;
              }
              nmcd.clrText = (clrText == (-1)) ? getForegroundPixel() : clrText;
              nmcd.clrTextBk = (clrTextBk == (-1)) ? getBackgroundPixel() : clrTextBk;
              OS.MoveMemory(lParam, nmcd, sizeof);
              return new LRESULT(OS.CDRF_NEWFONT);
          }
          break;
        }
      case OS.NM_DBLCLK:
        int pos = OS.GetMessagePos();
        TVHITTESTINFO lpht = new TVHITTESTINFO();
        POINT pt = new POINT();
        pt.x = ((short) (pos & 0xffff));
        pt.y = ((short) (pos >> 16));
        OS.ScreenToClient(handle, pt);
        lpht.x = pt.x;
        lpht.y = pt.y;
        OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
        if ((lpht.flags & OS.TVHT_ONITEM) == 0) {
          break;
        }
      case OS.NM_RETURN:
      case OS.TVN_SELCHANGEDA:
      case OS.TVN_SELCHANGEDW:
        if (!ignoreSelect) {
          TVITEM tvItem = null;
          if (code == OS.TVN_SELCHANGED) {
            tvItem = new TVITEM();
            int offset = (NMHDR.sizeof + 4) + TVITEM.sizeof;
            OS.MoveMemory(tvItem, lParam + offset, sizeof);
            hAnchor = tvItem.hItem;
          } else {
            int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
            if (hItem != 0) {
              tvItem = new TVITEM();
              tvItem.hItem = hItem;
              tvItem.mask = OS.TVIF_PARAM;
              OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
            }
          }
          Event event = new Event();
          if (tvItem != null) {
            event.item = items[tvItem.lParam];
          }
          if (code == OS.TVN_SELCHANGED) {
            postEvent(Selection, event);
          } else {
            postEvent(DefaultSelection, event);
          }
        }
        if ((code == OS.NM_DBLCLK) && hooks(DefaultSelection)) {
          return LRESULT.ONE;
        }
        break;
      case OS.TVN_SELCHANGINGA:
      case OS.TVN_SELCHANGINGW:
        if ((!ignoreSelect) && (!ignoreDeselect)) {
          hAnchor = 0;
          if ((style & SWT.MULTI) != 0) {
            deselectAll();
          }
        }
        break;
      case OS.TVN_ITEMEXPANDINGA:
      case OS.TVN_ITEMEXPANDINGW:
        if (!ignoreExpand) {
          TVITEM tvItem = new TVITEM();
          int offset = (NMHDR.sizeof + 4) + TVITEM.sizeof;
          OS.MoveMemory(tvItem, lParam + offset, sizeof);
          int[] action = new int[1];
          OS.MoveMemory(action, lParam + NMHDR.sizeof, 4);
          Event event = new Event();
          event.item = items[tvItem.lParam];
          if (action[0] == OS.TVE_EXPAND) {
            sendEvent(Expand, event);
            if (isDisposed()) {
              return LRESULT.ZERO;
            }
          }
          if (action[0] == OS.TVE_COLLAPSE) {
            sendEvent(Collapse, event);
            if (isDisposed()) {
              return LRESULT.ZERO;
            }
          }
        }
        break;
      case OS.TVN_BEGINDRAGA:
      case OS.TVN_BEGINDRAGW:
      case OS.TVN_BEGINRDRAGA:
      case OS.TVN_BEGINRDRAGW:
        TVITEM tvItem = new TVITEM();
        int offset = (NMHDR.sizeof + 4) + TVITEM.sizeof;
        OS.MoveMemory(tvItem, lParam + offset, sizeof);
        if ((tvItem.hItem != 0) && ((tvItem.state & OS.TVIS_SELECTED) == 0)) {
          ignoreSelect = ignoreDeselect = true;
          OS.SendMessage(handle, TVM_SELECTITEM, TVGN_CARET, tvItem.hItem);
          ignoreSelect = ignoreDeselect = false;
        }
        dragStarted = true;
        break;
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
