class PlaceHold {
  void destroyItem(TreeItem item, int hItem) {
    hFirstIndexOf = hLastIndexOf = 0;
    itemCount = -1;
    int hParent = 0;
    boolean fixRedraw = false;
    if ((style & SWT.DOUBLE_BUFFERED) == 0) {
      if ((drawCount == 0) && OS.IsWindowVisible(handle)) {
        RECT rect = new RECT();
        rect.left = hItem;
        fixRedraw = OS.SendMessage(handle, TVM_GETITEMRECT, 0, rect) == 0;
      }
    }
    if (fixRedraw) {
      hParent = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_PARENT, hItem);
      OS.UpdateWindow(handle);
      OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
    }
    if ((style & SWT.MULTI) != 0) {
      ignoreDeselect = ignoreSelect = lockSelection = true;
    }
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
      int hwndToolTip = OS.SendMessage(handle, TVM_GETTOOLTIPS, 0, 0);
      if (hwndToolTip != 0) {
        OS.SendMessage(hwndToolTip, TTM_POP, 0, 0);
      }
    }
    shrink = ignoreShrink = true;
    OS.SendMessage(handle, TVM_DELETEITEM, 0, hItem);
    ignoreShrink = false;
    if ((style & SWT.MULTI) != 0) {
      ignoreDeselect = ignoreSelect = lockSelection = false;
    }
    if (fixRedraw) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
      OS.ValidateRect(handle, null);
      if (OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CHILD, hParent) == 0) {
        RECT rect = new RECT();
        rect.left = hParent;
        if (OS.SendMessage(handle, TVM_GETITEMRECT, 0, rect) != 0) {
          OS.InvalidateRect(handle, rect, true);
        }
      }
    }
    int count = OS.SendMessage(handle, TVM_GETCOUNT, 0, 0);
    if (count == 0) {
      if (imageList != null) {
        OS.SendMessage(handle, TVM_SETIMAGELIST, 0, 0);
        display.releaseImageList(imageList);
      }
      imageList = null;
      if ((hwndParent == 0) && (!linesVisible)) {
        if (((!hooks(MeasureItem)) && (!hooks(EraseItem))) && (!hooks(PaintItem))) {
          customDraw = false;
        }
      }
      items = new TreeItem[4];
      scrollWidth = 0;
      setScrollWidth();
    }
    updateScrollBar();
  }
}
