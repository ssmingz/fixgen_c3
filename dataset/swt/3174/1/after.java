class PlaceHold {
  void createItem(TableColumn column, int index) {
    if (!((0 <= index) && (index <= columnCount))) {
      error(ERROR_INVALID_RANGE);
    }
    int oldColumn = OS.SendMessage(handle, LVM_GETSELECTEDCOLUMN, 0, 0);
    if (oldColumn >= index) {
      OS.SendMessage(handle, LVM_SETSELECTEDCOLUMN, oldColumn + 1, 0);
    }
    if (columnCount == columns.length) {
      TableColumn[] newColumns = new TableColumn[columns.length + 4];
      System.arraycopy(columns, 0, newColumns, 0, columns.length);
      columns = newColumns;
    }
    int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    for (int i = 0; i < itemCount; i++) {
      TableItem item = items[i];
      if (item != null) {
        String[] strings = item.strings;
        if (strings != null) {
          String[] temp = new String[columnCount + 1];
          System.arraycopy(strings, 0, temp, 0, index);
          System.arraycopy(strings, index, temp, index + 1, columnCount - index);
          item.strings = temp;
        }
        Image[] images = item.images;
        if (images != null) {
          Image[] temp = new Image[columnCount + 1];
          System.arraycopy(images, 0, temp, 0, index);
          System.arraycopy(images, index, temp, index + 1, columnCount - index);
          item.images = temp;
        }
        if (index == 0) {
          if (columnCount != 0) {
            if (strings == null) {
              item.strings = new String[columnCount + 1];
              item.strings[1] = item.text;
            }
            item.text = "";
            if (images == null) {
              item.images = new Image[columnCount + 1];
              item.images[1] = item.image;
            }
            item.image = null;
          }
        }
        if (item.cellBackground != null) {
          int[] cellBackground = item.cellBackground;
          int[] temp = new int[columnCount + 1];
          System.arraycopy(cellBackground, 0, temp, 0, index);
          System.arraycopy(cellBackground, index, temp, index + 1, columnCount - index);
          temp[index] = -1;
          item.cellBackground = temp;
        }
        if (item.cellForeground != null) {
          int[] cellForeground = item.cellForeground;
          int[] temp = new int[columnCount + 1];
          System.arraycopy(cellForeground, 0, temp, 0, index);
          System.arraycopy(cellForeground, index, temp, index + 1, columnCount - index);
          temp[index] = -1;
          item.cellForeground = temp;
        }
        if (item.cellFont != null) {
          int[] cellFont = item.cellFont;
          int[] temp = new int[columnCount + 1];
          System.arraycopy(cellFont, 0, temp, 0, index);
          System.arraycopy(cellFont, index, temp, index + 1, columnCount - index);
          temp[index] = -1;
          item.cellFont = temp;
        }
      }
    }
    System.arraycopy(columns, index, columns, index + 1, (columnCount++) - index);
    columns[index] = column;
    ignoreColumnResize = true;
    if (index == 0) {
      if (columnCount > 1) {
        LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = OS.LVCF_WIDTH;
        OS.SendMessage(handle, LVM_INSERTCOLUMN, 1, lvColumn);
        OS.SendMessage(handle, LVM_GETCOLUMN, 1, lvColumn);
        int width = lvColumn.cx;
        int cchTextMax = 1024;
        int hHeap = OS.GetProcessHeap();
        int byteCount = cchTextMax * TCHAR.sizeof;
        int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
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
      } else {
        setScrollWidth(0);
      }
      if ((parent.style & SWT.VIRTUAL) == 0) {
        LVITEM lvItem = new LVITEM();
        lvItem.mask = OS.LVIF_TEXT | OS.LVIF_IMAGE;
        lvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        lvItem.iImage = OS.I_IMAGECALLBACK;
        for (int i = 0; i < itemCount; i++) {
          lvItem.iItem = i;
          OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
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
    ignoreColumnResize = false;
    if (headerToolTipHandle != 0) {
      RECT rect = new RECT();
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      if (OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, rect) != 0) {
        TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        lpti.uFlags = OS.TTF_SUBCLASS;
        lpti.hwnd = hwndHeader;
        lpti.uId = column.id = display.nextToolTipId++;
        lpti.left = rect.left;
        lpti.top = rect.top;
        lpti.right = rect.right;
        lpti.bottom = rect.bottom;
        lpti.lpszText = OS.LPSTR_TEXTCALLBACK;
        OS.SendMessage(headerToolTipHandle, TTM_ADDTOOL, 0, lpti);
      }
    }
  }
}
