class PlaceHold {
  void createItem(TreeItem item, int hParent, int hInsertAfter, int hItem) {
    int id = -1;
    if (item != null) {
      id = (lastID < items.length) ? lastID : 0;
      while ((id < items.length) && (items[id] != null)) {
        id++;
      }
      if (id == items.length) {
        int length = 0;
        if ((drawCount == 0) && OS.IsWindowVisible(handle)) {
          length = items.length + 4;
        } else {
          shrink = true;
          length = Math.max(4, (items.length * 3) / 2);
        }
        TreeItem[] newItems = new TreeItem[length];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
      }
      lastID = id + 1;
    }
    int hNewItem = 0;
    int hFirstItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CHILD, hParent);
    boolean fixParent = hFirstItem == 0;
    if (hItem == 0) {
      TVINSERTSTRUCT tvInsert = new TVINSERTSTRUCT();
      tvInsert.hParent = hParent;
      tvInsert.hInsertAfter = hInsertAfter;
      tvInsert.lParam = id;
      tvInsert.pszText = OS.LPSTR_TEXTCALLBACK;
      tvInsert.iImage = tvInsert.iSelectedImage = OS.I_IMAGECALLBACK;
      tvInsert.mask =
          (((OS.TVIF_TEXT | OS.TVIF_IMAGE) | OS.TVIF_SELECTEDIMAGE) | OS.TVIF_HANDLE)
              | OS.TVIF_PARAM;
      if ((style & SWT.CHECK) != 0) {
        tvInsert.mask = tvInsert.mask | OS.TVIF_STATE;
        tvInsert.state = 1 << 12;
        tvInsert.stateMask = OS.TVIS_STATEIMAGEMASK;
      }
      hNewItem = OS.SendMessage(handle, TVM_INSERTITEM, 0, tvInsert);
      if (hNewItem == 0) {
        error(ERROR_ITEM_NOT_ADDED);
      }
    } else {
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
      tvItem.hItem = hNewItem = hItem;
      tvItem.lParam = id;
      OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
    }
    if (item != null) {
      item.handle = hNewItem;
      items[id] = item;
    }
    if (hFirstItem == 0) {
      switch (hInsertAfter) {
        case OS.TVI_FIRST:
        case OS.TVI_LAST:
          hFirstIndexOf = hLastIndexOf = hFirstItem = hNewItem;
          itemCount = lastIndexOf = 0;
      }
    }
    if ((hFirstItem == hFirstIndexOf) && (itemCount != (-1))) {
      itemCount++;
    }
    if (hItem == 0) {
      if (fixParent) {
        if ((drawCount == 0) && OS.IsWindowVisible(handle)) {
          RECT rect = new RECT();
          rect.left = hParent;
          if (OS.SendMessage(handle, TVM_GETITEMRECT, 0, rect) != 0) {
            OS.InvalidateRect(handle, rect, true);
          }
        }
      }
      if ((style & SWT.VIRTUAL) != 0) {
        if (currentItem != null) {
          RECT rect = new RECT();
          rect.left = hNewItem;
          if (OS.SendMessage(handle, TVM_GETITEMRECT, 0, rect) != 0) {
            RECT damageRect = new RECT();
            boolean damaged = OS.GetUpdateRect(handle, damageRect, true);
            if (damaged && (damageRect.top < rect.bottom)) {
              if (OS.IsWinCE) {
                OS.OffsetRect(damageRect, 0, rect.bottom - rect.top);
                OS.InvalidateRect(handle, damageRect, true);
              } else {
                int rgn = OS.CreateRectRgn(0, 0, 0, 0);
                int result = OS.GetUpdateRgn(handle, rgn, true);
                if (result != OS.NULLREGION) {
                  OS.OffsetRgn(rgn, 0, rect.bottom - rect.top);
                  OS.InvalidateRgn(handle, rgn, true);
                }
                OS.DeleteObject(rgn);
              }
            }
          }
        }
      }
      updateScrollBar();
    }
  }
}
