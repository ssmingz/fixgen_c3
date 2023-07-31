class PlaceHold {
  LRESULT wmNotifyHeader(NMHDR hdr, int wParam, int lParam) {
    switch (hdr.code) {
      case OS.HDN_BEGINTRACKW:
      case OS.HDN_BEGINTRACKA:
      case OS.HDN_DIVIDERDBLCLICKW:
      case OS.HDN_DIVIDERDBLCLICKA:
        {
          if (columnCount == 0) {
            return LRESULT.ONE;
          }
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          TableColumn column = columns[phdn.iItem];
          if ((column != null) && (!column.getResizable())) {
            return LRESULT.ONE;
          }
          ignoreColumnMove = true;
          switch (hdr.code) {
            case OS.HDN_DIVIDERDBLCLICKW:
            case OS.HDN_DIVIDERDBLCLICKA:
              boolean fixPack = false;
              if ((!OS.IsWinCE) && (OS.WIN32_VERSION < OS.VERSION(6, 0))) {
                fixPack = (phdn.iItem == 0) && (!firstColumnImage);
              }
              if ((column != null) && (fixPack || hooks(MeasureItem))) {
                column.pack();
                return LRESULT.ONE;
              }
          }
          break;
        }
      case OS.NM_RELEASEDCAPTURE:
        {
          if (!ignoreColumnMove) {
            for (int i = 0; i < columnCount; i++) {
              TableColumn column = columns[i];
              column.updateToolTip(i);
            }
          }
          ignoreColumnMove = false;
          break;
        }
      case OS.HDN_BEGINDRAG:
        {
          if (ignoreColumnMove) {
            return LRESULT.ONE;
          }
          int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
          if ((bits & OS.LVS_EX_HEADERDRAGDROP) == 0) {
            break;
          }
          if (columnCount == 0) {
            return LRESULT.ONE;
          }
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          if (phdn.iItem != (-1)) {
            TableColumn column = columns[phdn.iItem];
            if ((column != null) && (!column.getMoveable())) {
              ignoreColumnMove = true;
              return LRESULT.ONE;
            }
          }
          break;
        }
      case OS.HDN_ENDDRAG:
        {
          int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
          if ((bits & OS.LVS_EX_HEADERDRAGDROP) == 0) {
            break;
          }
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          if ((phdn.iItem != (-1)) && (phdn.pitem != 0)) {
            HDITEM pitem = new HDITEM();
            OS.MoveMemory(pitem, phdn.pitem, sizeof);
            if (((pitem.mask & OS.HDI_ORDER) != 0) && (pitem.iOrder != (-1))) {
              if (columnCount == 0) {
                break;
              }
              int[] order = new int[columnCount];
              OS.SendMessage(handle, LVM_GETCOLUMNORDERARRAY, columnCount, order);
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
              ignoreColumnMove = false;
              for (int i = start; i <= end; i++) {
                TableColumn column = columns[order[i]];
                if (!column.isDisposed()) {
                  column.postEvent(Move);
                }
              }
            }
          }
          break;
        }
      case OS.HDN_ITEMCHANGEDW:
      case OS.HDN_ITEMCHANGEDA:
        {
          int width = ((int) (OS.SendMessage(handle, LVM_GETCOLUMNWIDTH, 0, 0)));
          if ((lastWidth == 0) && (width > 0)) {
            int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
            if ((bits & OS.LVS_EX_GRIDLINES) != 0) {
              RECT rect = new RECT();
              OS.GetClientRect(handle, rect);
              rect.right = rect.left + width;
              OS.InvalidateRect(handle, rect, true);
            }
          }
          lastWidth = width;
          if (!ignoreColumnResize) {
            NMHEADER phdn = new NMHEADER();
            OS.MoveMemory(phdn, lParam, sizeof);
            if (phdn.pitem != 0) {
              HDITEM pitem = new HDITEM();
              OS.MoveMemory(pitem, phdn.pitem, sizeof);
              if ((pitem.mask & OS.HDI_WIDTH) != 0) {
                TableColumn column = columns[phdn.iItem];
                if (column != null) {
                  column.updateToolTip(phdn.iItem);
                  column.sendEvent(Resize);
                  if (isDisposed()) {
                    return LRESULT.ZERO;
                  }
                  TableColumn[] newColumns = new TableColumn[columnCount];
                  System.arraycopy(columns, 0, newColumns, 0, columnCount);
                  int[] order = new int[columnCount];
                  OS.SendMessage(handle, LVM_GETCOLUMNORDERARRAY, columnCount, order);
                  boolean moved = false;
                  for (int i = 0; i < columnCount; i++) {
                    TableColumn nextColumn = newColumns[order[i]];
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
            }
          }
          break;
        }
      case OS.HDN_ITEMDBLCLICKW:
      case OS.HDN_ITEMDBLCLICKA:
        {
          NMHEADER phdn = new NMHEADER();
          OS.MoveMemory(phdn, lParam, sizeof);
          TableColumn column = columns[phdn.iItem];
          if (column != null) {
            column.postEvent(DefaultSelection);
          }
          break;
        }
    }
    return null;
  }
}
