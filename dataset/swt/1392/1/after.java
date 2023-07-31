class PlaceHold {
  void updateScrollBar() {
    if (hwndParent != 0) {
      if ((columnCount != 0) || (scrollWidth != 0)) {
        SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = OS.SIF_ALL;
        int itemCount = ((int) (OS.SendMessage(handle, TVM_GETCOUNT, 0, 0)));
        if (itemCount == 0) {
          OS.GetScrollInfo(hwndParent, SB_VERT, info);
          info.nPage = info.nMax + 1;
          OS.SetScrollInfo(hwndParent, SB_VERT, info, true);
        } else {
          OS.GetScrollInfo(handle, SB_VERT, info);
          if (info.nPage == 0) {
            SCROLLBARINFO psbi = new SCROLLBARINFO();
            psbi.cbSize = SCROLLBARINFO.sizeof;
            OS.GetScrollBarInfo(handle, OBJID_VSCROLL, psbi);
            if ((psbi.rgstate[0] & OS.STATE_SYSTEM_INVISIBLE) != 0) {
              info.nPage = info.nMax + 1;
            }
          }
          OS.SetScrollInfo(hwndParent, SB_VERT, info, true);
        }
      }
    }
  }
}
