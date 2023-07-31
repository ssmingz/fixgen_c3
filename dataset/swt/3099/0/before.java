class PlaceHold {
  void createItem(TableColumn column, int index) {
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    int columnCount = count + 1;
    if ((count == 1) && (columns[0] == null)) {
      count = 0;
    }
    if (!((0 <= index) && (index <= count))) {
      error(ERROR_INVALID_RANGE);
    }
    if (count == columns.length) {
      TableColumn[] newColumns = new TableColumn[columns.length + 4];
      System.arraycopy(columns, 0, newColumns, 0, columns.length);
      columns = newColumns;
    }
    if (customDraw && (items != null)) {
      int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
      for (int i = 0; i < itemCount; i++) {
        if (items[i].cellBackground != null) {
          int[] cellBackground = items[i].cellBackground;
          int[] temp = new int[columnCount];
          System.arraycopy(cellBackground, 0, temp, 0, index);
          System.arraycopy(cellBackground, index, temp, index + 1, (columnCount - index) - 1);
          temp[index] = -1;
          items[i].cellBackground = temp;
        }
        if (items[i].cellForeground != null) {
          int[] cellForeground = items[i].cellForeground;
          int[] temp = new int[columnCount];
          System.arraycopy(cellForeground, 0, temp, 0, index);
          System.arraycopy(cellForeground, index, temp, index + 1, (columnCount - index) - 1);
          temp[index] = -1;
          items[i].cellForeground = temp;
        }
      }
    }
    System.arraycopy(columns, index, columns, index + 1, count - index);
    columns[index] = column;
    if (index == 0) {
      if (count > 0) {
        LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = OS.LVCF_WIDTH;
        OS.SendMessage(handle, LVM_INSERTCOLUMN, 1, lvColumn);
        OS.SendMessage(handle, LVM_GETCOLUMN, 1, lvColumn);
        int width = lvColumn.cx;
        int cchTextMax = 1024;
        int hHeap = OS.GetProcessHeap();
        int byteCount = cchTextMax * TCHAR.sizeof;
        int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
        LVITEM lvItem = new LVITEM();
        lvItem.mask = (OS.LVIF_TEXT | OS.LVIF_IMAGE) | OS.LVIF_STATE;
        int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
        for (int i = 0; i < itemCount; i++) {
          lvItem.iItem = i;
          lvItem.iSubItem = 0;
          lvItem.pszText = pszText;
          lvItem.cchTextMax = cchTextMax;
          OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
          lvItem.iSubItem = 1;
          OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
          lvItem.iSubItem = 0;
          lvItem.pszText = lvItem.cchTextMax = 0;
          lvItem.iImage = OS.I_IMAGENONE;
          OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
          items[i].text = "";
          items[i].image = null;
        }
        lvColumn.mask = ((OS.LVCF_TEXT | OS.LVCF_IMAGE) | OS.LVCF_WIDTH) | OS.LVCF_FMT;
        lvColumn.pszText = pszText;
        lvColumn.cchTextMax = cchTextMax;
        OS.SendMessage(handle, LVM_GETCOLUMN, 0, lvColumn);
        OS.SendMessage(handle, LVM_SETCOLUMN, 1, lvColumn);
        lvColumn.fmt = OS.LVCFMT_IMAGE;
        lvColumn.cx = width;
        lvColumn.iImage = OS.I_IMAGENONE;
        lvColumn.pszText = lvColumn.cchTextMax = 0;
        OS.SendMessage(handle, LVM_SETCOLUMN, 0, lvColumn);
        lvColumn.mask = OS.LVCF_FMT;
        lvColumn.fmt = OS.LVCFMT_LEFT;
        OS.SendMessage(handle, LVM_SETCOLUMN, 0, lvColumn);
        if (pszText != 0) {
          OS.HeapFree(hHeap, 0, pszText);
        }
      }
    } else {
      int fmt = OS.LVCFMT_LEFT;
      if ((column.style & SWT.CENTER) == SWT.CENTER) {
        fmt = OS.LVCFMT_CENTER;
      }
      if ((column.style & SWT.RIGHT) == SWT.RIGHT) {
        fmt = OS.LVCFMT_RIGHT;
      }
      LVCOLUMN lvColumn = new LVCOLUMN();
      lvColumn.mask = OS.LVCF_WIDTH | OS.LVCF_FMT;
      lvColumn.fmt = fmt;
      OS.SendMessage(handle, LVM_INSERTCOLUMN, index, lvColumn);
    }
  }
}
