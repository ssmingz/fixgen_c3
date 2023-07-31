class PlaceHold {
  void showItem(int hItem) {
    if (OS.SendMessage(handle, TVM_GETVISIBLECOUNT, 0, 0) == 0) {
      OS.SendMessage(handle, TVM_SELECTITEM, TVGN_FIRSTVISIBLE, hItem);
      OS.SendMessage(handle, WM_HSCROLL, SB_TOP, 0);
    } else {
      boolean scroll = true;
      RECT itemRect = new RECT();
      itemRect.left = hItem;
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
      if (scroll) {
        OS.SendMessage(handle, TVM_ENSUREVISIBLE, 0, hItem);
      }
    }
  }
}
