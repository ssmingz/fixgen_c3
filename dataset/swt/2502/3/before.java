class PlaceHold {
  public void pack() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    int columnWidth = 0;
    int hwnd = parent.handle;
    int hwndHeader = parent.hwndHeader;
    RECT headerRect = new RECT();
    OS.SendMessage(hwndHeader, HDM_GETITEMRECT, index, headerRect);
    int hDC = OS.GetDC(hwnd);
    int oldFont = 0;
    int newFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
    if (newFont != 0) {
      oldFont = OS.SelectObject(hDC, newFont);
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
    tvItem.hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    while (tvItem.hItem != 0) {
      OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
      TreeItem item = (tvItem.lParam != (-1)) ? parent.items[((int) (tvItem.lParam))] : null;
      if (item != null) {
        int hFont = (item.cellFont != null) ? item.cellFont[index] : -1;
        if (hFont == (-1)) {
          hFont = item.font;
        }
        if (hFont != (-1)) {
          hFont = OS.SelectObject(hDC, hFont);
        }
        RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
        if (hFont != (-1)) {
          OS.SelectObject(hDC, hFont);
        }
        if (parent.hooks(MeasureItem)) {
          int nSavedDC = OS.SaveDC(hDC);
          GCData data = new GCData();
          data.device = display;
          data.hFont = hFont;
          GC gc = GC.win32_new(hDC, data);
          Event event = new Event();
          event.item = item;
          event.gc = gc;
          event.index = index;
          event.x = itemRect.left;
          event.y = itemRect.top;
          event.width = itemRect.right - itemRect.left;
          event.height = itemRect.bottom - itemRect.top;
          parent.sendEvent(MeasureItem, event);
          event.gc = null;
          gc.dispose();
          OS.RestoreDC(hDC, nSavedDC);
          if (isDisposed() || parent.isDisposed()) {
            break;
          }
          if (event.height > parent.getItemHeight()) {
            parent.setItemHeight(event.height);
          }
          itemRect.right = event.x + event.width;
        }
        columnWidth = Math.max(columnWidth, itemRect.right - headerRect.left);
      }
      tvItem.hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, tvItem.hItem);
    }
    RECT rect = new RECT();
    int flags = OS.DT_CALCRECT | OS.DT_NOPREFIX;
    TCHAR buffer = new TCHAR(parent.getCodePage(), text, false);
    OS.DrawText(hDC, buffer, buffer.length(), rect, flags);
    int headerWidth = (rect.right - rect.left) + Tree.HEADER_MARGIN;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      headerWidth += Tree.HEADER_EXTRA;
    }
    if ((image != null) || (parent.sortColumn == this)) {
      Image headerImage = null;
      if ((parent.sortColumn == this) && (parent.sortDirection != SWT.NONE)) {
        if (OS.COMCTL32_MAJOR < 6) {
          headerImage = display.getSortImage(parent.sortDirection);
        } else {
          headerWidth += Tree.SORT_WIDTH;
        }
      } else {
        headerImage = image;
      }
      if (headerImage != null) {
        Rectangle bounds = headerImage.getBounds();
        headerWidth += bounds.width;
      }
      int margin = 0;
      if ((hwndHeader != 0) && (OS.COMCTL32_VERSION >= OS.VERSION(5, 80))) {
        margin = ((int) (OS.SendMessage(hwndHeader, HDM_GETBITMAPMARGIN, 0, 0)));
      } else {
        margin = OS.GetSystemMetrics(SM_CXEDGE) * 3;
      }
      headerWidth += margin * 2;
    }
    if (newFont != 0) {
      OS.SelectObject(hDC, oldFont);
    }
    OS.ReleaseDC(hwnd, hDC);
    int gridWidth = (parent.linesVisible) ? Tree.GRID_WIDTH : 0;
    setWidth(Math.max(headerWidth, columnWidth + gridWidth));
  }
}
