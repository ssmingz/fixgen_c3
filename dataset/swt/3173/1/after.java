class PlaceHold {
  LRESULT wmNotifyChild(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    switch (hdr.code) {
      case OS.LVN_ODFINDITEMA:
      case OS.LVN_ODFINDITEMW:
        {
          if ((style & SWT.VIRTUAL) != 0) {
            NMLVFINDITEM pnmfi = new NMLVFINDITEM();
            OS.MoveMemory(pnmfi, lParam, sizeof);
            int index = Math.max(0, pnmfi.iStart - 1);
            return new LRESULT(index);
          }
          break;
        }
      case OS.LVN_GETDISPINFOA:
      case OS.LVN_GETDISPINFOW:
        {
          NMLVDISPINFO plvfi = new NMLVDISPINFO();
          OS.MoveMemory(plvfi, lParam, sizeof);
          lastIndexOf = plvfi.iItem;
          TableItem item = _getItem(plvfi.iItem);
          if (!item.cached) {
            if ((style & SWT.VIRTUAL) != 0) {
              if (!checkData(item, false)) {
                break;
              }
              TableItem newItem = (fixScrollWidth) ? null : item;
              if (setScrollWidth(newItem, true)) {
                redraw();
              }
            }
            item.cached = true;
          }
          if ((plvfi.mask & OS.LVIF_TEXT) != 0) {
            String string = null;
            if (plvfi.iSubItem == 0) {
              string = item.text;
            } else {
              String[] strings = item.strings;
              if (strings != null) {
                string = strings[plvfi.iSubItem];
              }
            }
            if (string != null) {
              TCHAR buffer = new TCHAR(getCodePage(), string, false);
              int byteCount = Math.min(buffer.length(), plvfi.cchTextMax - 1) * TCHAR.sizeof;
              OS.MoveMemory(plvfi.pszText, buffer, byteCount);
              OS.MoveMemory(plvfi.pszText + byteCount, new byte[TCHAR.sizeof], sizeof);
              plvfi.cchTextMax = Math.min(plvfi.cchTextMax, string.length() + 1);
            }
          }
          if ((plvfi.mask & OS.LVIF_IMAGE) != 0) {
            Image image = null;
            if (plvfi.iSubItem == 0) {
              image = item.image;
            } else {
              Image[] images = item.images;
              if (images != null) {
                image = images[plvfi.iSubItem];
              }
            }
            if (image != null) {
              plvfi.iImage = imageIndex(image);
            }
          }
          if ((plvfi.mask & OS.LVIF_STATE) != 0) {
            if (plvfi.iSubItem == 0) {
              int state = 1;
              if (item.checked) {
                state++;
              }
              if (item.grayed) {
                state += 2;
              }
              plvfi.state = state << 12;
              plvfi.stateMask = OS.LVIS_STATEIMAGEMASK;
            }
          }
          if ((plvfi.mask & OS.LVIF_INDENT) != 0) {
            if (plvfi.iSubItem == 0) {
              plvfi.iIndent = item.imageIndent;
            }
          }
          OS.MoveMemory(lParam, plvfi, sizeof);
          break;
        }
      case OS.NM_CUSTOMDRAW:
        {
          if (!customDraw) {
            break;
          }
          NMLVCUSTOMDRAW nmcd = new NMLVCUSTOMDRAW();
          OS.MoveMemory(nmcd, lParam, sizeof);
          switch (nmcd.dwDrawStage) {
            case OS.CDDS_PREPAINT:
              return new LRESULT(OS.CDRF_NOTIFYITEMDRAW);
            case OS.CDDS_ITEMPREPAINT:
              return new LRESULT(OS.CDRF_NOTIFYSUBITEMDRAW);
            case OS.CDDS_ITEMPREPAINT | OS.CDDS_SUBITEM:
              {
                TableItem item = _getItem(nmcd.dwItemSpec);
                int hFont = (item.cellFont != null) ? item.cellFont[nmcd.iSubItem] : -1;
                if (hFont == (-1)) {
                  hFont = item.font;
                }
                int clrText =
                    (item.cellForeground != null) ? item.cellForeground[nmcd.iSubItem] : -1;
                if (clrText == (-1)) {
                  clrText = item.foreground;
                }
                int clrTextBk =
                    (item.cellBackground != null) ? item.cellBackground[nmcd.iSubItem] : -1;
                if (clrTextBk == (-1)) {
                  clrTextBk = item.background;
                }
                if (((hFont == (-1)) && (clrText == (-1))) && (clrTextBk == (-1))) {
                  if (((item.cellForeground == null) && (item.cellBackground == null))
                      && (item.cellFont == null)) {
                    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
                    int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
                    if (count == 1) {
                      break;
                    }
                  }
                }
                if (hFont == (-1)) {
                  hFont = OS.SendMessage(handle, WM_GETFONT, 0, 0);
                }
                OS.SelectObject(nmcd.hdc, hFont);
                nmcd.clrText = (clrText == (-1)) ? getForegroundPixel() : clrText;
                nmcd.clrTextBk = (clrTextBk == (-1)) ? getBackgroundPixel() : clrTextBk;
                OS.MoveMemory(lParam, nmcd, sizeof);
                return new LRESULT(OS.CDRF_NEWFONT);
              }
          }
          break;
        }
      case OS.LVN_MARQUEEBEGIN:
        return LRESULT.ONE;
      case OS.LVN_BEGINDRAG:
      case OS.LVN_BEGINRDRAG:
        {
          dragStarted = true;
          if (hdr.code == OS.LVN_BEGINDRAG) {
            int pos = OS.GetMessagePos();
            POINT pt = new POINT();
            pt.x = ((short) (pos & 0xffff));
            pt.y = ((short) (pos >> 16));
            OS.ScreenToClient(handle, pt);
            Event event = new Event();
            event.x = pt.x;
            event.y = pt.y;
            postEvent(DragDetect, event);
          }
          break;
        }
      case OS.LVN_COLUMNCLICK:
        {
          NMLISTVIEW pnmlv = new NMLISTVIEW();
          OS.MoveMemory(pnmlv, lParam, sizeof);
          TableColumn column = columns[pnmlv.iSubItem];
          if (column != null) {
            column.postEvent(Selection);
          }
          break;
        }
      case OS.LVN_ITEMACTIVATE:
        {
          NMLISTVIEW pnmlv = new NMLISTVIEW();
          OS.MoveMemory(pnmlv, lParam, sizeof);
          if (pnmlv.iItem != (-1)) {
            Event event = new Event();
            event.item = _getItem(pnmlv.iItem);
            postEvent(DefaultSelection, event);
          }
          break;
        }
      case OS.LVN_ITEMCHANGED:
        {
          if (!ignoreSelect) {
            NMLISTVIEW pnmlv = new NMLISTVIEW();
            OS.MoveMemory(pnmlv, lParam, sizeof);
            if ((pnmlv.iItem != (-1)) && ((pnmlv.uChanged & OS.LVIF_STATE) != 0)) {
              boolean isFocus = (pnmlv.uNewState & OS.LVIS_FOCUSED) != 0;
              int index = OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED);
              if ((style & SWT.MULTI) != 0) {
                if (OS.GetKeyState(VK_CONTROL) < 0) {
                  if (!isFocus) {
                    if (index == pnmlv.iItem) {
                      boolean isSelected = (pnmlv.uNewState & OS.LVIS_SELECTED) != 0;
                      boolean wasSelected = (pnmlv.uOldState & OS.LVIS_SELECTED) != 0;
                      isFocus = isSelected != wasSelected;
                    }
                  } else {
                    isFocus = mouseDown;
                  }
                }
              }
              if (OS.GetKeyState(VK_SPACE) < 0) {
                isFocus = true;
              }
              if (isFocus) {
                Event event = new Event();
                if (index != (-1)) {
                  event.item = _getItem(index);
                }
                postEvent(Selection, event);
              }
            }
          }
          break;
        }
      case OS.NM_RECOGNIZEGESTURE:
        if (OS.IsPPC) {
          boolean hasMenu = (menu != null) && (!menu.isDisposed());
          if ((!hasMenu) && (!hooks(MenuDetect))) {
            return LRESULT.ONE;
          }
        }
        break;
      case OS.GN_CONTEXTMENU:
        if (OS.IsPPC) {
          boolean hasMenu = (menu != null) && (!menu.isDisposed());
          if (hasMenu || hooks(MenuDetect)) {
            NMRGINFO nmrg = new NMRGINFO();
            OS.MoveMemory(nmrg, lParam, sizeof);
            showMenu(menu, nmrg.x, nmrg.y);
            return LRESULT.ONE;
          }
        }
        break;
    }
    return super.wmNotifyChild(wParam, lParam);
  }
}
