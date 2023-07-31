class PlaceHold {
  void createItem(TreeColumn column, int index) {
    if (hwndHeader == 0) {
      createParent();
    }
    int columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    if (!((0 <= index) && (index <= columnCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (columnCount == columns.length) {
      TreeColumn[] newColumns = new TreeColumn[columns.length + 4];
      System.arraycopy(columns, 0, newColumns, 0, columns.length);
      columns = newColumns;
    }
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
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
    System.arraycopy(columns, index, columns, index + 1, columnCount - index);
    columns[index] = column;
    int hHeap = OS.GetProcessHeap();
    int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, sizeof);
    HDITEM hdItem = new HDITEM();
    hdItem.mask = OS.HDI_TEXT | OS.HDI_FORMAT;
    hdItem.pszText = pszText;
    if ((column.style & SWT.LEFT) == SWT.LEFT) {
      hdItem.fmt = OS.HDF_LEFT;
    }
    if ((column.style & SWT.CENTER) == SWT.CENTER) {
      hdItem.fmt = OS.HDF_CENTER;
    }
    if ((column.style & SWT.RIGHT) == SWT.RIGHT) {
      hdItem.fmt = OS.HDF_RIGHT;
    }
    OS.SendMessage(hwndHeader, HDM_INSERTITEM, index, hdItem);
    if (pszText != 0) {
      OS.HeapFree(hHeap, 0, pszText);
    }
    if (columnCount == 0) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      bits |= OS.TVS_NOHSCROLL;
      if ((style & SWT.FULL_SELECTION) != 0) {
        bits &= ~OS.TVS_FULLROWSELECT;
      }
      OS.SetWindowLong(handle, GWL_STYLE, bits);
    }
    setScrollWidth();
    updateScrollBar();
    if ((columnCount == 0) && (OS.SendMessage(handle, TVM_GETCOUNT, 0, 0) != 0)) {
      OS.InvalidateRect(handle, null, true);
    }
  }
}
