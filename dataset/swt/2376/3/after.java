class PlaceHold {
  Event sendPaintItemEvent(
      TreeItem item, NMTTCUSTOMDRAW nmcd, int column, RECT itemRect, int hFont) {
    int nSavedDC = OS.SaveDC(nmcd.hdc);
    RECT insetRect = toolTipInset(itemRect);
    OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, null);
    GCData data = new GCData();
    data.device = display;
    data.font = Font.win32_new(display, hFont);
    data.foreground = OS.GetTextColor(nmcd.hdc);
    data.background = OS.GetBkColor(nmcd.hdc);
    GC gc = GC.win32_new(nmcd.hdc, data);
    Event event = new Event();
    event.item = item;
    event.index = column;
    event.gc = gc;
    event.detail |= SWT.FOREGROUND;
    event.x = itemRect.left;
    event.y = itemRect.top;
    event.width = itemRect.right - itemRect.left;
    event.height = itemRect.bottom - itemRect.top;
    sendEvent(PaintItem, event);
    event.gc = null;
    gc.dispose();
    OS.RestoreDC(nmcd.hdc, nSavedDC);
    return event;
  }
}
