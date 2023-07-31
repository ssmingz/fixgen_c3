class PlaceHold {
  public void pack() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    int columnWidth = 0;
    int hwnd = parent.handle;
    int hDC = OS.GetDC(hwnd);
    int oldFont = 0;
    int newFont = OS.SendMessage(hwnd, WM_GETFONT, 0, 0);
    if (newFont != 0) {
      oldFont = OS.SelectObject(hDC, newFont);
    }
    int cp = parent.getCodePage();
    RECT rect = new RECT();
    int flags = OS.DT_CALCRECT | OS.DT_NOPREFIX;
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_PARAM;
    tvItem.hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    while (tvItem.hItem != 0) {
      OS.SendMessage(hwnd, TVM_GETITEM, 0, tvItem);
      TreeItem item = (tvItem.lParam != (-1)) ? parent.items[tvItem.lParam] : null;
      if (item != null) {
        if (index == 0) {
          if ((((parent.style & SWT.VIRTUAL) == 0) && (!item.cached)) && (!parent.painted)) {
            tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
            tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
            OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
            tvItem.mask = OS.TVIF_PARAM;
          }
          rect.left = item.handle;
          if (OS.SendMessage(hwnd, TVM_GETITEMRECT, 1, rect) != 0) {
            columnWidth = Math.max(columnWidth, rect.right);
          }
        } else {
          int imageWidth = 0;
          int textWidth = 0;
          Image image = (item.images != null) ? item.images[index] : null;
          if (image != null) {
            Rectangle bounds = image.getBounds();
            imageWidth = bounds.width;
          }
          String string = (item.strings != null) ? item.strings[index] : null;
          if (string != null) {
            TCHAR buffer = new TCHAR(cp, string, false);
            OS.DrawText(hDC, buffer, buffer.length(), rect, flags);
            textWidth = rect.right - rect.left;
          }
          columnWidth = Math.max(columnWidth, (imageWidth + textWidth) + (Tree.INSET * 3));
        }
      }
      tvItem.hItem = OS.SendMessage(hwnd, TVM_GETNEXTITEM, TVGN_NEXTVISIBLE, tvItem.hItem);
    }
    TCHAR buffer = new TCHAR(cp, text, false);
    OS.DrawText(hDC, buffer, buffer.length(), rect, flags);
    int headerWidth = (rect.right - rect.left) + Tree.HEADER_MARGIN;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      headerWidth += Tree.HEADER_EXTRA;
    }
    if ((image != null) || (parent.sortColumn == this)) {
      Image headerImage = null;
      if ((parent.sortColumn == this) && (parent.sortDirection != SWT.NULL)) {
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
      int hwndHeader = parent.hwndHeader;
      if ((hwndHeader != 0) && (OS.COMCTL32_VERSION >= OS.VERSION(5, 80))) {
        margin = OS.SendMessage(hwndHeader, HDM_GETBITMAPMARGIN, 0, 0);
      } else {
        margin = OS.GetSystemMetrics(SM_CXEDGE) * 3;
      }
      headerWidth += margin * 2;
    }
    if (newFont != 0) {
      OS.SelectObject(hDC, oldFont);
    }
    OS.ReleaseDC(hwnd, hDC);
    setWidth(Math.max(headerWidth, columnWidth));
  }
}
