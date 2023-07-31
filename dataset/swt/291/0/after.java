class PlaceHold {
  LRESULT wmNotifyHeader(NMHDR hdr, int wParam, int lParam) {
    switch (hdr.code) {
      case OS.HDN_BEGINTRACKW:
      case OS.HDN_BEGINTRACKA:
      case OS.HDN_DIVIDERDBLCLICKW:
      case OS.HDN_DIVIDERDBLCLICKA:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          TreeColumn column = columns[phdn.iItem];
          if ((column != null) && (!column.getResizable())) {
            return LRESULT.ONE;
          }
          ignoreColumnMove = true;
          switch (hdr.code) {
            case OS.HDN_DIVIDERDBLCLICKW:
            case OS.HDN_DIVIDERDBLCLICKA:
              if (column != null) {
                column.pack();
              }
          }
          break;
        }
      case OS.NM_RELEASEDCAPTURE:
        {
          if (!ignoreColumnMove) {
            for (int i = 0; i < columnCount; i++) {
              TreeColumn column = columns[i];
              column.updateToolTip(i);
            }
            updateImageList();
          }
          ignoreColumnMove = false;
          break;
        }
      case OS.HDN_BEGINDRAG:
        {
          if (ignoreColumnMove) {
            return LRESULT.ONE;
          }
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          if (phdn.iItem != (-1)) {
            TreeColumn column = columns[phdn.iItem];
            if ((column != null) && (!column.getMoveable())) {
              ignoreColumnMove = true;
              return LRESULT.ONE;
            }
          }
          break;
        }
      case OS.HDN_ENDDRAG:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          if ((phdn.iItem != (-1)) && (phdn.pitem != 0)) {
            HDITEM pitem = new HDITEM();
            OS.MoveMemory(pitem, phdn.pitem, sizeof);
            if (((pitem.mask & OS.HDI_ORDER) != 0) && (pitem.iOrder != (-1))) {
              int[] order = new int[columnCount];
              OS.SendMessage(hwndHeader, HDM_GETORDERARRAY, columnCount, order);
              int index = 0;
              while (index < order.length) {
                if (order[index] == phdn.iItem) {
                  break;
                }
                index++;
              }
              if (index == order.length) {
                index = 0;
              }
              if (index == pitem.iOrder) {
                break;
              }
              int start = Math.min(index, pitem.iOrder);
              int end = Math.max(index, pitem.iOrder);
              RECT rect = new RECT();
              RECT headerRect = new RECT();
              OS.GetClientRect(handle, rect);
              OS.SendMessage(hwndHeader, HDM_GETITEMRECT, order[start], headerRect);
              rect.left = Math.max(rect.left, headerRect.left);
              OS.SendMessage(hwndHeader, HDM_GETITEMRECT, order[end], headerRect);
              rect.right = Math.min(rect.right, headerRect.right);
              OS.InvalidateRect(handle, rect, true);
              ignoreColumnMove = false;
              for (int i = start; i <= end; i++) {
                TreeColumn column = columns[order[i]];
                if (!column.isDisposed()) {
                  column.postEvent(Move);
                }
              }
            }
          }
          break;
        }
      case OS.HDN_ITEMCHANGINGW:
      case OS.HDN_ITEMCHANGINGA:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          if (phdn.pitem != 0) {
            HDITEM newItem = new HDITEM();
            OS.MoveMemory(newItem, phdn.pitem, sizeof);
            if ((newItem.mask & OS.HDI_WIDTH) != 0) {
              RECT rect = new RECT();
              OS.GetClientRect(handle, rect);
              HDITEM oldItem = new HDITEM();
              oldItem.mask = OS.HDI_WIDTH;
              OS.SendMessage(hwndHeader, HDM_GETITEM, phdn.iItem, oldItem);
              int deltaX = newItem.cxy - oldItem.cxy;
              RECT headerRect = new RECT();
              OS.SendMessage(hwndHeader, HDM_GETITEMRECT, phdn.iItem, headerRect);
              int gridWidth = (linesVisible) ? GRID_WIDTH : 0;
              rect.left = headerRect.right - gridWidth;
              int newX = rect.left + deltaX;
              rect.right = Math.max(rect.right, rect.left + Math.abs(deltaX));
              if (explorerTheme
                  || ((((findImageControl() != null) || hooks(MeasureItem)) || hooks(EraseItem))
                      || hooks(PaintItem))) {
                rect.left -= OS.GetSystemMetrics(SM_CXFOCUSBORDER);
                OS.InvalidateRect(handle, rect, true);
                OS.OffsetRect(rect, deltaX, 0);
                OS.InvalidateRect(handle, rect, true);
              } else {
                int flags = OS.SW_INVALIDATE | OS.SW_ERASE;
                OS.ScrollWindowEx(handle, deltaX, 0, rect, null, 0, null, flags);
              }
              if (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, phdn.iItem, 0) != 0) {
                rect.left = headerRect.left;
                rect.right = newX;
                OS.InvalidateRect(handle, rect, true);
              }
              setScrollWidth();
            }
          }
          break;
        }
      case OS.HDN_ITEMCHANGEDW:
      case OS.HDN_ITEMCHANGEDA:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          if (phdn.pitem != 0) {
            HDITEM pitem = new HDITEM();
            OS.MoveMemory(pitem, phdn.pitem, sizeof);
            if ((pitem.mask & OS.HDI_WIDTH) != 0) {
              if (ignoreColumnMove) {
                if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
                  int flags = OS.RDW_UPDATENOW | OS.RDW_ALLCHILDREN;
                  OS.RedrawWindow(handle, null, 0, flags);
                } else if ((style & SWT.DOUBLE_BUFFERED) == 0) {
                  int oldStyle = style;
                  style |= SWT.DOUBLE_BUFFERED;
                  OS.UpdateWindow(handle);
                  style = oldStyle;
                }
              }
              TreeColumn column = columns[phdn.iItem];
              if (column != null) {
                column.updateToolTip(phdn.iItem);
                column.sendEvent(Resize);
                if (isDisposed()) {
                  return LRESULT.ZERO;
                }
                TreeColumn[] newColumns = new TreeColumn[columnCount];
                System.arraycopy(columns, 0, newColumns, 0, columnCount);
                int[] order = new int[columnCount];
                OS.SendMessage(hwndHeader, HDM_GETORDERARRAY, columnCount, order);
                boolean moved = false;
                for (int i = 0; i < columnCount; i++) {
                  TreeColumn nextColumn = newColumns[order[i]];
                  if (moved && (!nextColumn.isDisposed())) {
                    nextColumn.updateToolTip(order[i]);
                    nextColumn.sendEvent(Move);
                  }
                  if (nextColumn == column) {
                    moved = true;
                  }
                }
              }
            }
            setScrollWidth();
          }
          break;
        }
      case OS.HDN_ITEMCLICKW:
      case OS.HDN_ITEMCLICKA:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          TreeColumn column = columns[phdn.iItem];
          if (column != null) {
            column.sendSelectionEvent(Selection);
          }
          break;
        }
      case OS.HDN_ITEMDBLCLICKW:
      case OS.HDN_ITEMDBLCLICKA:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          TreeColumn column = columns[phdn.iItem];
          if (column != null) {
            column.sendSelectionEvent(DefaultSelection);
          }
          break;
        }
    }
    return null;
  }
}
