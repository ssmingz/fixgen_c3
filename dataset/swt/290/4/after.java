class PlaceHold {
  void setItemCount(int count, int hParent, int hItem) {
    boolean redraw = false;
    if (OS.SendMessage(handle, TVM_GETCOUNT, 0, 0) == 0) {
      redraw = getDrawing() && OS.IsWindowVisible(handle);
      if (redraw) {
        OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
      }
    }
    int itemCount = 0;
    while ((hItem != 0) && (itemCount < count)) {
      hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXT, hItem);
      itemCount++;
    }
    boolean expanded = false;
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
    if ((!redraw) && ((style & SWT.VIRTUAL) != 0)) {
      if (OS.IsWinCE) {
        tvItem.hItem = hParent;
        tvItem.mask = OS.TVIF_STATE;
        OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
        expanded = (tvItem.state & OS.TVIS_EXPANDED) != 0;
      } else {
        int state = ((int) (OS.SendMessage(handle, TVM_GETITEMSTATE, hParent, TVIS_EXPANDED)));
        expanded = (state & OS.TVIS_EXPANDED) != 0;
      }
    }
    while (hItem != 0) {
      tvItem.hItem = hItem;
      OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
      hItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_NEXT, hItem);
      TreeItem item = (tvItem.lParam != (-1)) ? items[((int) (tvItem.lParam))] : null;
      if ((item != null) && (!item.isDisposed())) {
        item.dispose();
      } else {
        releaseItem(tvItem.hItem, tvItem, false);
        destroyItem(null, tvItem.hItem);
      }
    }
    if ((style & SWT.VIRTUAL) != 0) {
      for (int i = itemCount; i < count; i++) {
        if (expanded) {
          ignoreShrink = true;
        }
        createItem(null, hParent, TVI_LAST, 0);
        if (expanded) {
          ignoreShrink = false;
        }
      }
    } else {
      shrink = true;
      int extra = Math.max(4, ((count + 3) / 4) * 4);
      TreeItem[] newItems = new TreeItem[items.length + extra];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
      for (int i = itemCount; i < count; i++) {
        new TreeItem(this, SWT.NONE, hParent, OS.TVI_LAST, 0);
      }
    }
    if (redraw) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
      OS.InvalidateRect(handle, null, true);
    }
  }
}
