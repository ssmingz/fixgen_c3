class PlaceHold {
  LRESULT wmNotifyChild(NMHDR hdr, int wParam, int lParam) {
    switch (hdr.code) {
      case OS.LVN_ODFINDITEMA:
      case OS.LVN_ODFINDITEMW:
        {
          if ((style & SWT.VIRTUAL) != 0) {
            return new LRESULT(-1);
          }
          break;
        }
      case OS.LVN_GETDISPINFOA:
      case OS.LVN_GETDISPINFOW:
        {
          NMLVDISPINFO plvfi = new NMLVDISPINFO();
          OS.MoveMemory(plvfi, lParam, sizeof);
          if ((style & SWT.VIRTUAL) != 0) {
            if (ignoreShrink) {
              OS.SendMessage(handle, LVM_REDRAWITEMS, plvfi.iItem, plvfi.iItem);
              break;
            }
          }
          TableItem item = _getItem(plvfi.iItem);
          if (item == null) {
            break;
          }
          if (!item.cached) {
            if ((style & SWT.VIRTUAL) != 0) {
              lastIndexOf = plvfi.iItem;
              if (!checkData(item, lastIndexOf, false)) {
                break;
              }
              TableItem newItem = (fixScrollWidth) ? null : item;
              if (setScrollWidth(newItem, true)) {
                OS.InvalidateRect(handle, null, true);
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
              int length = Math.min(string.length(), plvfi.cchTextMax - 1);
              if (((!tipRequested) && (plvfi.iSubItem == 0)) && (length == 0)) {
                string = " ";
                length = 1;
              }
              char[] buffer = display.tableBuffer;
              if ((buffer == null) || (plvfi.cchTextMax > buffer.length)) {
                buffer = display.tableBuffer = new char[plvfi.cchTextMax];
              }
              string.getChars(0, length, buffer, 0);
              buffer[length++] = 0;
              if (OS.IsUnicode) {
                OS.MoveMemory(plvfi.pszText, buffer, length * 2);
              } else {
                OS.WideCharToMultiByte(
                    getCodePage(), 0, buffer, length, plvfi.pszText, plvfi.cchTextMax, null, null);
                OS.MoveMemory((plvfi.pszText + plvfi.cchTextMax) - 1, new byte[1], 1);
              }
            }
          }
          boolean move = false;
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
              plvfi.iImage = imageIndex(image, plvfi.iSubItem);
              move = true;
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
              move = true;
            }
          }
          if ((plvfi.mask & OS.LVIF_INDENT) != 0) {
            if (plvfi.iSubItem == 0) {
              plvfi.iIndent = item.imageIndent;
              move = true;
            }
          }
          if (move) {
            OS.MoveMemory(lParam, plvfi, sizeof);
          }
          break;
        }
      case OS.NM_CUSTOMDRAW:
        {
          int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
          if (hdr.hwndFrom == hwndHeader) {
            break;
          }
          if ((!customDraw) && (findImageControl() == null)) {
            if (OS.IsWindowEnabled(handle)) {
              break;
            }
          }
          NMLVCUSTOMDRAW nmcd = new NMLVCUSTOMDRAW();
          OS.MoveMemory(nmcd, lParam, sizeof);
          switch (nmcd.dwDrawStage) {
            case OS.CDDS_PREPAINT:
              return CDDS_PREPAINT(wParam, lParam);
            case OS.CDDS_ITEMPREPAINT:
              return CDDS_ITEMPREPAINT(wParam, lParam);
            case OS.CDDS_SUBITEMPREPAINT:
              return CDDS_SUBITEMPREPAINT(wParam, lParam);
            case OS.CDDS_SUBITEMPOSTPAINT:
              return CDDS_SUBITEMPOSTPAINT(wParam, lParam);
            case OS.CDDS_POSTPAINT:
              return CDDS_POSTPAINT(wParam, lParam);
          }
          break;
        }
      case OS.LVN_MARQUEEBEGIN:
        {
          if ((style & SWT.SINGLE) != 0) {
            return LRESULT.ONE;
          }
          if (hooks(MouseDown) || hooks(MouseUp)) {
            return LRESULT.ONE;
          }
          break;
        }
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
            sendDragEvent(1, pt.x, pt.y);
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
          if (ignoreActivate) {
            break;
          }
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
            if ((pnmlv.uChanged & OS.LVIF_STATE) != 0) {
              if (pnmlv.iItem == (-1)) {
                wasSelected = true;
              } else {
                boolean oldSelected = (pnmlv.uOldState & OS.LVIS_SELECTED) != 0;
                boolean newSelected = (pnmlv.uNewState & OS.LVIS_SELECTED) != 0;
                if (oldSelected != newSelected) {
                  wasSelected = true;
                }
              }
            }
          }
          if (hooks(EraseItem) || hooks(PaintItem)) {
            int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
            int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
            if (count != 0) {
              forceResize();
              RECT rect = new RECT();
              OS.GetClientRect(handle, rect);
              NMLISTVIEW pnmlv = new NMLISTVIEW();
              OS.MoveMemory(pnmlv, lParam, sizeof);
              if (pnmlv.iItem != (-1)) {
                RECT itemRect = new RECT();
                itemRect.left = OS.LVIR_BOUNDS;
                ignoreCustomDraw = true;
                OS.SendMessage(handle, LVM_GETITEMRECT, pnmlv.iItem, itemRect);
                ignoreCustomDraw = false;
                RECT headerRect = new RECT();
                int index = OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, count - 1, 0);
                OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect);
                OS.MapWindowPoints(hwndHeader, handle, headerRect, 2);
                rect.left = headerRect.right;
                rect.top = itemRect.top;
                rect.bottom = itemRect.bottom;
              }
              OS.InvalidateRect(handle, rect, true);
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
            showMenu(nmrg.x, nmrg.y);
            return LRESULT.ONE;
          }
        }
        break;
    }
    return super.wmNotifyChild(hdr, wParam, lParam);
  }
}
