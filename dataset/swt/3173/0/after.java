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
              if (item == null) {
                break;
              }
              TVITEM tvItem = new TVITEM();
              tvItem.mask = OS.TVIF_STATE;
              tvItem.hItem = item.handle;
              OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
              int hFont = item.font;
              int clrText = item.foreground;
              int clrTextBk = item.background;
              if (((hFont == (-1)) && (clrText == (-1))) && (clrTextBk == (-1))) {
                break;
              }
              if (hFont != (-1)) {
                OS.SelectObject(nmcd.hdc, hFont);
              }
              if ((tvItem.state & (OS.TVIS_SELECTED | OS.TVIS_DROPHILITED)) == 0) {
                nmcd.clrText = (clrText == (-1)) ? getForegroundPixel() : clrText;
                nmcd.clrTextBk = (clrTextBk == (-1)) ? getBackgroundPixel() : clrTextBk;
              }
              OS.MoveMemory(lParam, nmcd, sizeof);
              return new LRESULT(OS.CDRF_NEWFONT);
          }
          break;
        }
      case OS.NM_DBLCLK:
        {
          if (!ignoreSelect) {
            int pos = OS.GetMessagePos();
            POINT pt = new POINT();
            pt.x = ((short) (pos & 0xffff));
            pt.y = ((short) (pos >> 16));
            OS.ScreenToClient(handle, pt);
            TVHITTESTINFO lpht = new TVHITTESTINFO();
            lpht.x = pt.x;
            lpht.y = pt.y;
            OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
            if ((lpht.flags & OS.TVHT_ONITEM) == 0) {
              break;
            }
            Event event = new Event();
            int hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CARET, 0);
            if (hItem != 0) {
              TVITEM tvItem = new TVITEM();
              tvItem.hItem = hItem;
              tvItem.mask = OS.TVIF_PARAM;
              OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
              event.item = items[tvItem.lParam];
            }
            postEvent(DefaultSelection, event);
          }
          if (hooks(DefaultSelection)) {
            return LRESULT.ONE;
          }
          break;
        }
      case OS.TVN_SELCHANGEDA:
      case OS.TVN_SELCHANGEDW:
        {
          if ((style & SWT.MULTI) != 0) {
            if (lockSelection) {
              if (oldSelected) {
                TVITEM tvItem = new TVITEM();
                int offset = NMHDR.sizeof + 4;
                OS.MoveMemory(tvItem, lParam + offset, sizeof);
                tvItem.mask = OS.TVIF_STATE;
                tvItem.stateMask = OS.TVIS_SELECTED;
                tvItem.state = OS.TVIS_SELECTED;
                OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
              }
              if ((!newSelected) && ignoreSelect) {
                TVITEM tvItem = new TVITEM();
                int offset = (NMHDR.sizeof + 4) + TVITEM.sizeof;
                OS.MoveMemory(tvItem, lParam + offset, sizeof);
                tvItem.mask = OS.TVIF_STATE;
                tvItem.stateMask = OS.TVIS_SELECTED;
                tvItem.state = 0;
                OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
              }
            }
          }
          if (!ignoreSelect) {
            TVITEM tvItem = new TVITEM();
            int offset = (NMHDR.sizeof + 4) + TVITEM.sizeof;
            OS.MoveMemory(tvItem, lParam + offset, sizeof);
            hAnchor = tvItem.hItem;
            Event event = new Event();
            event.item = items[tvItem.lParam];
            postEvent(Selection, event);
          }
          break;
        }
      case OS.TVN_SELCHANGINGA:
      case OS.TVN_SELCHANGINGW:
        {
          if ((style & SWT.MULTI) != 0) {
            if (lockSelection) {
              TVITEM tvItem = new TVITEM();
              int offset1 = NMHDR.sizeof + 4;
              OS.MoveMemory(tvItem, lParam + offset1, sizeof);
              oldSelected = (tvItem.state & OS.TVIS_SELECTED) != 0;
              int offset2 = (NMHDR.sizeof + 4) + TVITEM.sizeof;
              OS.MoveMemory(tvItem, lParam + offset2, sizeof);
              newSelected = (tvItem.state & OS.TVIS_SELECTED) != 0;
            }
          }
          if ((!ignoreSelect) && (!ignoreDeselect)) {
            hAnchor = 0;
            if ((style & SWT.MULTI) != 0) {
              deselectAll();
            }
          }
          break;
        }
      case OS.TVN_ITEMEXPANDINGA:
      case OS.TVN_ITEMEXPANDINGW:
        {
          if (!ignoreExpand) {
            TVITEM tvItem = new TVITEM();
            int offset = (NMHDR.sizeof + 4) + TVITEM.sizeof;
            OS.MoveMemory(tvItem, lParam + offset, sizeof);
            TreeItem item = items[tvItem.lParam];
            if (item == null) {
              break;
            }
            Event event = new Event();
            event.item = item;
            int[] action = new int[1];
            OS.MoveMemory(action, lParam + NMHDR.sizeof, 4);
            switch (action[0]) {
              case OS.TVE_EXPAND:
                if ((tvItem.state & OS.TVIS_EXPANDED) == 0) {
                  sendEvent(Expand, event);
                  if (isDisposed()) {
                    return LRESULT.ZERO;
                  }
                }
                break;
              case OS.TVE_COLLAPSE:
                sendEvent(Collapse, event);
                if (isDisposed()) {
                  return LRESULT.ZERO;
                }
                break;
            }
          }
          break;
        }
      case OS.TVN_BEGINDRAGA:
      case OS.TVN_BEGINDRAGW:
      case OS.TVN_BEGINRDRAGA:
      case OS.TVN_BEGINRDRAGW:
        {
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
      case OS.NM_RECOGNIZEGESTURE:
        {
          if (OS.IsPPC) {
            boolean hasMenu = (menu != null) && (!menu.isDisposed());
            if ((!hasMenu) && (!hooks(MenuDetect))) {
              return LRESULT.ONE;
            }
          }
          break;
        }
      case OS.GN_CONTEXTMENU:
        {
          if (OS.IsPPC) {
            boolean hasMenu = (menu != null) && (!menu.isDisposed());
            if (hasMenu || hooks(MenuDetect)) {
              NMRGINFO nmrg = new NMRGINFO();
              OS.MoveMemory(nmrg, lParam, sizeof);
              showMenu(menu, nmrg.x, nmrg.y);
              gestureCompleted = true;
              return LRESULT.ONE;
            }
          }
          break;
        }
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
