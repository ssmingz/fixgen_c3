class PlaceHold {
  void destroyItem(TreeColumn column) {
    if (hwndHeader == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    int columnCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
    int index = 0;
    while (index < columnCount) {
      if (columns[index] == column) {
        break;
      }
      index++;
    }
    if (OS.SendMessage(hwndHeader, HDM_DELETEITEM, index, 0) == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    System.arraycopy(columns, index + 1, columns, index, (--columnCount) - index);
    columns[columnCount] = null;
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if (item != null) {
        if (columnCount == 0) {
          item.strings = null;
          item.images = null;
          item.cellBackground = null;
          item.cellForeground = null;
          item.cellFont = null;
        } else {
          if (item.strings != null) {
            String[] strings = item.strings;
            if (index == 0) {
              item.text = (strings[1] != null) ? strings[1] : "";
            }
            String[] temp = new String[columnCount];
            System.arraycopy(strings, 0, temp, 0, index);
            System.arraycopy(strings, index + 1, temp, index, columnCount - index);
            item.strings = temp;
          } else if (index == 0) {
            item.text = "";
          }
          if (item.images != null) {
            Image[] images = item.images;
            if (index == 0) {
              item.image = images[1];
            }
            Image[] temp = new Image[columnCount];
            System.arraycopy(images, 0, temp, 0, index);
            System.arraycopy(images, index + 1, temp, index, columnCount - index);
            item.images = temp;
          } else if (index == 0) {
            item.image = null;
          }
          if (item.cellBackground != null) {
            int[] cellBackground = item.cellBackground;
            int[] temp = new int[columnCount];
            System.arraycopy(cellBackground, 0, temp, 0, index);
            System.arraycopy(cellBackground, index + 1, temp, index, columnCount - index);
            item.cellBackground = temp;
          }
          if (item.cellForeground != null) {
            int[] cellForeground = item.cellForeground;
            int[] temp = new int[columnCount];
            System.arraycopy(cellForeground, 0, temp, 0, index);
            System.arraycopy(cellForeground, index + 1, temp, index, columnCount - index);
            item.cellForeground = temp;
          }
          if (item.cellFont != null) {
            int[] cellFont = item.cellFont;
            int[] temp = new int[columnCount];
            System.arraycopy(cellFont, 0, temp, 0, index);
            System.arraycopy(cellFont, index + 1, temp, index, columnCount - index);
            item.cellFont = temp;
          }
        }
      }
    }
    if (columnCount == 0) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      bits &= ~OS.TVS_NOHSCROLL;
      if ((style & SWT.FULL_SELECTION) != 0) {
        bits |= OS.TVS_FULLROWSELECT;
      }
      OS.SetWindowLong(handle, GWL_STYLE, bits);
      OS.InvalidateRect(handle, null, true);
    } else {
      if (index == 0) {
        columns[0].style &= ~((SWT.LEFT | SWT.RIGHT) | SWT.CENTER);
        columns[0].style |= SWT.LEFT;
        HDITEM hdItem = new HDITEM();
        hdItem.mask = OS.HDI_FORMAT | OS.HDI_IMAGE;
        OS.SendMessage(hwndHeader, HDM_GETITEM, index, hdItem);
        hdItem.fmt &= ~OS.HDF_JUSTIFYMASK;
        hdItem.fmt |= OS.HDF_LEFT;
        OS.SendMessage(hwndHeader, HDM_SETITEM, index, hdItem);
      }
      RECT rect = new RECT();
      RECT itemRect = new RECT();
      OS.GetClientRect(handle, rect);
      OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index - 1, itemRect);
      rect.left = itemRect.right;
      OS.InvalidateRect(handle, rect, true);
    }
    setScrollWidth();
  }
}
