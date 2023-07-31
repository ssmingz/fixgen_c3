class PlaceHold {
  void destroyItem(TableColumn column) {
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    int count = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    int index = 0;
    while (index < count) {
      if (columns[index] == column) {
        break;
      }
      index++;
    }
    if (index == count) {
      return;
    }
    boolean first = false;
    if (index == 0) {
      first = true;
      if (count > 1) {
        index = 1;
        int cchTextMax = 1024;
        int hHeap = OS.GetProcessHeap();
        int byteCount = cchTextMax * TCHAR.sizeof;
        int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, byteCount);
        LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = OS.LVCF_TEXT | OS.LVCF_WIDTH;
        lvColumn.pszText = pszText;
        lvColumn.cchTextMax = cchTextMax;
        OS.SendMessage(handle, LVM_GETCOLUMN, 1, lvColumn);
        lvColumn.mask |= OS.LVCF_FMT;
        lvColumn.fmt = OS.LVCFMT_LEFT;
        OS.SendMessage(handle, LVM_SETCOLUMN, 0, lvColumn);
        LVITEM lvItem = new LVITEM();
        lvItem.mask = (OS.LVIF_TEXT | OS.LVIF_IMAGE) | OS.LVIF_STATE;
        lvItem.pszText = pszText;
        lvItem.cchTextMax = cchTextMax;
        int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
        for (int i = 0; i < itemCount; i++) {
          lvItem.iItem = i;
          lvItem.iSubItem = 1;
          OS.SendMessage(handle, LVM_GETITEM, 0, lvItem);
          lvItem.iSubItem = 0;
          OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
          TCHAR buffer = new TCHAR(getCodePage(), cchTextMax);
          OS.MoveMemory(buffer, pszText, byteCount);
          items[i].text = buffer.toString(0, buffer.strlen());
          if (imageList != null) {
            items[i].image = imageList.get(lvItem.iImage);
          }
        }
        if (pszText != 0) {
          OS.HeapFree(hHeap, 0, pszText);
        }
      } else {
        int hHeap = OS.GetProcessHeap();
        int pszText = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, sizeof);
        LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = OS.LVCF_TEXT;
        lvColumn.pszText = pszText;
        OS.SendMessage(handle, LVM_SETCOLUMN, 0, lvColumn);
        if (pszText != 0) {
          OS.HeapFree(hHeap, 0, pszText);
        }
        OS.SendMessage(handle, LVM_SETCOLUMNWIDTH, 0, LVSCW_AUTOSIZE);
      }
    }
    if (count > 1) {
      if (OS.SendMessage(handle, LVM_DELETECOLUMN, index, 0) == 0) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
    }
    if (first) {
      index = 0;
    }
    System.arraycopy(columns, index + 1, columns, index, (--count) - index);
    columns[count] = null;
    if (customDraw && (items != null)) {
      int columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
      int itemCount = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
      for (int i = 0; i < itemCount; i++) {
        if (items[i].cellBackground != null) {
          int[] cellBackground = items[i].cellBackground;
          int[] temp = new int[columnCount];
          System.arraycopy(cellBackground, 0, temp, 0, index);
          System.arraycopy(cellBackground, index + 1, temp, index, columnCount - index);
          items[i].cellBackground = temp;
        }
        if (items[i].cellForeground != null) {
          int[] cellForeground = items[i].cellForeground;
          int[] temp = new int[columnCount];
          System.arraycopy(cellForeground, 0, temp, 0, index);
          System.arraycopy(cellForeground, index + 1, temp, index, columnCount - index);
          items[i].cellForeground = temp;
        }
      }
    }
  }
}
