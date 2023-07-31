class PlaceHold {
  void showDropTargetEffect(int effect, int x, int y) {
    effect = checkEffect(effect);
    int handle = tree.handle;
    Point coordinates = new Point(x, y);
    coordinates = tree.toControl(coordinates);
    TVHITTESTINFO lpht = new TVHITTESTINFO();
    lpht.x = coordinates.x;
    lpht.y = coordinates.y;
    OS.SendMessage(handle, TVM_HITTEST, 0, lpht);
    int hItem = lpht.hItem;
    if ((effect & DND.FEEDBACK_SCROLL) == 0) {
      scrollBeginTime = 0;
      scrollIndex = -1;
    } else if (((hItem != (-1)) && (scrollIndex == hItem)) && (scrollBeginTime != 0)) {
      if (System.currentTimeMillis() >= scrollBeginTime) {
        int topItem = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_FIRSTVISIBLE, 0);
        int nextItem =
            OS.SendMessage(
                handle,
                TVM_GETNEXTITEM,
                hItem == topItem ? OS.TVGN_PREVIOUSVISIBLE : OS.TVGN_NEXTVISIBLE,
                hItem);
        boolean scroll = true;
        if (hItem == topItem) {
          scroll = nextItem != 0;
        } else {
          RECT itemRect = new RECT();
          itemRect.left = nextItem;
          if (OS.SendMessage(handle, TVM_GETITEMRECT, 1, itemRect) != 0) {
            RECT rect = new RECT();
            OS.GetClientRect(handle, rect);
            POINT pt = new POINT();
            pt.x = itemRect.left;
            pt.y = itemRect.top;
            if (OS.PtInRect(rect, pt)) {
              pt.y = itemRect.bottom;
              if (OS.PtInRect(rect, pt)) {
                scroll = false;
              }
            }
          }
        }
        if (scroll) {
          OS.SendMessage(handle, TVM_ENSUREVISIBLE, 0, nextItem);
          tree.redraw();
        }
        scrollBeginTime = 0;
        scrollIndex = -1;
      }
    } else {
      scrollBeginTime = System.currentTimeMillis() + SCROLL_HYSTERESIS;
      scrollIndex = hItem;
    }
    if ((effect & DND.FEEDBACK_EXPAND) == 0) {
      expandBeginTime = 0;
      expandIndex = -1;
    } else if (((hItem != (-1)) && (expandIndex == hItem)) && (expandBeginTime != 0)) {
      if (System.currentTimeMillis() >= expandBeginTime) {
        if (OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_CHILD, hItem) != 0) {
          TVITEM tvItem = new TVITEM();
          tvItem.hItem = hItem;
          tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
          OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
          if ((tvItem.state & OS.TVIS_EXPANDED) == 0) {
            OS.SendMessage(handle, TVM_EXPAND, TVE_EXPAND, hItem);
            tree.redraw();
          }
        }
        expandBeginTime = 0;
        expandIndex = -1;
      }
    } else {
      expandBeginTime = System.currentTimeMillis() + EXPAND_HYSTERESIS;
      expandIndex = hItem;
    }
    if ((dropIndex != (-1)) && ((dropIndex != hItem) || ((effect & DND.FEEDBACK_SELECT) == 0))) {
      TVITEM tvItem = new TVITEM();
      tvItem.hItem = dropIndex;
      tvItem.mask = OS.TVIF_STATE;
      tvItem.stateMask = OS.TVIS_DROPHILITED;
      tvItem.state = 0;
      OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
      dropIndex = -1;
    }
    if (((hItem != (-1)) && (hItem != dropIndex)) && ((effect & DND.FEEDBACK_SELECT) != 0)) {
      TVITEM tvItem = new TVITEM();
      tvItem.hItem = hItem;
      tvItem.mask = OS.TVIF_STATE;
      tvItem.stateMask = OS.TVIS_DROPHILITED;
      tvItem.state = OS.TVIS_DROPHILITED;
      OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
      dropIndex = hItem;
    }
    if (((effect & DND.FEEDBACK_INSERT_BEFORE) != 0)
        || ((effect & DND.FEEDBACK_INSERT_AFTER) != 0)) {
      boolean before = (effect & DND.FEEDBACK_INSERT_BEFORE) != 0;
      TreeItem item = ((TreeItem) (tree.getDisplay().findWidget(tree.handle, hItem)));
      if (item != null) {
        if ((item != insertItem) || (before != insertBefore)) {
          tree.setInsertMark(item, before);
        }
        insertItem = item;
        insertBefore = before;
      } else {
        if (insertItem != null) {
          tree.setInsertMark(null, false);
        }
        insertItem = null;
      }
    } else {
      if (insertItem != null) {
        tree.setInsertMark(null, false);
      }
      insertItem = null;
    }
    return;
  }
}
