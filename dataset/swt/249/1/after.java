class PlaceHold {
  public void pack() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    int hwnd = parent.handle;
    int oldWidth = OS.SendMessage(hwnd, LVM_GETCOLUMNWIDTH, index, 0);
    TCHAR buffer = new TCHAR(parent.getCodePage(), text, true);
    int headerWidth = OS.SendMessage(hwnd, LVM_GETSTRINGWIDTH, 0, buffer) + Table.HEADER_MARGIN;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      headerWidth += Table.HEADER_EXTRA;
    }
    boolean hasHeaderImage = false;
    if ((image != null) || (parent.sortColumn == this)) {
      hasHeaderImage = true;
      Image headerImage = null;
      if ((parent.sortColumn == this) && (parent.sortDirection != SWT.NONE)) {
        if (OS.COMCTL32_MAJOR < 6) {
          headerImage = display.getSortImage(parent.sortDirection);
        } else {
          headerWidth += Table.SORT_WIDTH;
        }
      } else {
        headerImage = image;
      }
      if (headerImage != null) {
        Rectangle bounds = headerImage.getBounds();
        headerWidth += bounds.width;
      }
      int margin = 0;
      if (OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
        int hwndHeader = OS.SendMessage(hwnd, LVM_GETHEADER, 0, 0);
        margin = OS.SendMessage(hwndHeader, HDM_GETBITMAPMARGIN, 0, 0);
      } else {
        margin = OS.GetSystemMetrics(SM_CXEDGE) * 3;
      }
      headerWidth += margin * 4;
    }
    parent.ignoreColumnResize = true;
    int columnWidth = 0;
    if (((index == 0) && (!parent.firstColumnImage)) || parent.hooks(MeasureItem)) {
      RECT headerRect = new RECT();
      int hwndHeader = OS.SendMessage(hwnd, LVM_GETHEADER, 0, 0);
      OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect);
      OS.MapWindowPoints(hwndHeader, hwnd, headerRect, 2);
      int hDC = OS.GetDC(hwnd);
      int oldFont = 0;
      int newFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
      if (newFont != 0) {
        oldFont = OS.SelectObject(hDC, newFont);
      }
      int count = OS.SendMessage(hwnd, LVM_GETITEMCOUNT, 0, 0);
      for (int i = 0; i < count; i++) {
        TableItem item = parent.items[i];
        if (item != null) {
          int hFont = (item.cellFont != null) ? item.cellFont[index] : -1;
          if (hFont == (-1)) {
            hFont = item.font;
          }
          if (hFont != (-1)) {
            hFont = OS.SelectObject(hDC, hFont);
          }
          Event event = parent.sendMeasureItemEvent(item, i, index, hDC);
          if (hFont != (-1)) {
            hFont = OS.SelectObject(hDC, hFont);
          }
          if (isDisposed() || parent.isDisposed()) {
            break;
          }
          columnWidth = Math.max(columnWidth, (event.x + event.width) - headerRect.left);
        }
      }
      if (newFont != 0) {
        OS.SelectObject(hDC, oldFont);
      }
      OS.ReleaseDC(hwnd, hDC);
      OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, columnWidth);
    } else {
      OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, LVSCW_AUTOSIZE);
      columnWidth = OS.SendMessage(hwnd, LVM_GETCOLUMNWIDTH, index, 0);
      if (index == 0) {
        if (parent.imageList == null) {
          columnWidth += 2;
        }
        if ((parent.style & SWT.CHECK) != 0) {
          int hStateList = OS.SendMessage(hwnd, LVM_GETIMAGELIST, LVSIL_STATE, 0);
          if (hStateList != 0) {
            int[] cx = new int[1];
            int[] cy = new int[1];
            OS.ImageList_GetIconSize(hStateList, cx, cy);
            columnWidth += cx[0];
          }
        }
      }
    }
    if (headerWidth > columnWidth) {
      if (!hasHeaderImage) {
        RECT rect = null;
        boolean fixWidth = index == (parent.getColumnCount() - 1);
        if (fixWidth) {
          rect = new RECT();
          OS.GetWindowRect(hwnd, rect);
          OS.UpdateWindow(hwnd);
          int flags = ((OS.SWP_NOACTIVATE | OS.SWP_NOMOVE) | OS.SWP_NOREDRAW) | OS.SWP_NOZORDER;
          SetWindowPos(hwnd, 0, 0, 0, 0, rect.bottom - rect.top, flags);
        }
        OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, LVSCW_AUTOSIZE_USEHEADER);
        if (fixWidth) {
          int flags = (OS.SWP_NOACTIVATE | OS.SWP_NOMOVE) | OS.SWP_NOZORDER;
          SetWindowPos(hwnd, 0, 0, 0, rect.right - rect.left, rect.bottom - rect.top, flags);
        }
      } else {
        OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, headerWidth);
      }
    } else if (index == 0) {
      OS.SendMessage(hwnd, LVM_SETCOLUMNWIDTH, index, columnWidth);
    }
    parent.ignoreColumnResize = false;
    int newWidth = OS.SendMessage(hwnd, LVM_GETCOLUMNWIDTH, index, 0);
    if (oldWidth != newWidth) {
      updateToolTip(index);
      sendEvent(Resize);
      if (isDisposed()) {
        return;
      }
      boolean moved = false;
      int[] order = parent.getColumnOrder();
      TableColumn[] columns = parent.getColumns();
      for (int i = 0; i < order.length; i++) {
        TableColumn column = columns[order[i]];
        if (moved && (!column.isDisposed())) {
          column.updateToolTip(order[i]);
          column.sendEvent(Move);
        }
        if (column == this) {
          moved = true;
        }
      }
    }
  }
}
