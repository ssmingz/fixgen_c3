class PlaceHold {
  LRESULT WM_NOTIFY(int wParam, int lParam) {
    NMHDR hdr = new NMHDR();
    OS.MoveMemory(hdr, lParam, sizeof);
    if ((hwndHeader != 0) && (hdr.hwndFrom == hwndHeader)) {
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
              int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
              for (int i = 0; i < count; i++) {
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
                int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
                int[] order = new int[count];
                OS.SendMessage(hwndHeader, HDM_GETORDERARRAY, count, order);
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
                RECT itemRect = new RECT();
                OS.GetClientRect(handle, rect);
                OS.SendMessage(hwndHeader, HDM_GETITEMRECT, order[start], itemRect);
                rect.left = Math.max(rect.left, itemRect.left);
                OS.SendMessage(hwndHeader, HDM_GETITEMRECT, order[end], itemRect);
                rect.right = Math.min(rect.right, itemRect.right);
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
                RECT itemRect = new RECT();
                int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
                int index = OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, count - 1, 0);
                OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, itemRect);
                rect.right = Math.max(rect.right, itemRect.right);
                OS.SendMessage(hwndHeader, HDM_GETITEMRECT, phdn.iItem, itemRect);
                int gridWidth = (getLinesVisible()) ? GRID_WIDTH : 0;
                rect.left = itemRect.right - gridWidth;
                if (findImageControl() != null) {
                  OS.InvalidateRect(handle, rect, true);
                } else {
                  HDITEM oldItem = new HDITEM();
                  oldItem.mask = OS.HDI_WIDTH;
                  OS.SendMessage(hwndHeader, HDM_GETITEM, phdn.iItem, oldItem);
                  int deltaX = newItem.cxy - oldItem.cxy;
                  int flags = OS.SW_INVALIDATE | OS.SW_ERASE;
                  OS.ScrollWindowEx(handle, deltaX, 0, rect, null, 0, null, flags);
                }
                if (phdn.iItem != 0) {
                  rect.left = itemRect.left;
                  rect.right = itemRect.right;
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
                TreeColumn column = columns[phdn.iItem];
                if (column != null) {
                  column.updateToolTip(phdn.iItem);
                  column.sendEvent(Resize);
                  if (isDisposed()) {
                    return LRESULT.ZERO;
                  }
                  int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
                  TreeColumn[] newColumns = new TreeColumn[count];
                  System.arraycopy(columns, 0, newColumns, 0, count);
                  int[] order = new int[count];
                  OS.SendMessage(hwndHeader, HDM_GETORDERARRAY, count, order);
                  boolean moved = false;
                  for (int i = 0; i < count; i++) {
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
              column.postEvent(Selection);
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
              column.postEvent(DefaultSelection);
            }
            break;
          }
      }
    }
    return super.WM_NOTIFY(wParam, lParam);
  }
}
