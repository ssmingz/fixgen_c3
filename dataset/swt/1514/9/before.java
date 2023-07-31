class PlaceHold {
  void setScrollbar() {
    if (itemCount == 0) {
      return;
    }
    RECT rect = new RECT();
    OS.GetClientRect(handle, rect);
    int height = rect.bottom - rect.top;
    ExpandItem item = items[itemCount - 1];
    int maxHeight = (item.y + ExpandBar.HEADER_HEIGHT) + spacing;
    if (item.expanded) {
      maxHeight += item.height;
    }
    if ((yCurrentScroll > 0) && (height > maxHeight)) {
      yCurrentScroll = Math.max(0, (yCurrentScroll + maxHeight) - height);
      layoutItems(0, false);
    }
    maxHeight += yCurrentScroll;
    SCROLLINFO info = new SCROLLINFO();
    info.cbSize = SCROLLINFO.sizeof;
    info.fMask = (OS.SIF_RANGE | OS.SIF_PAGE) | OS.SIF_POS;
    info.nMin = 0;
    info.nMax = maxHeight;
    info.nPage = height;
    info.nPos = Math.min(yCurrentScroll, info.nMax);
    if (info.nPage != 0) {
      info.nPage++;
    }
    OS.SetScrollInfo(handle, SB_VERT, info, true);
  }
}
