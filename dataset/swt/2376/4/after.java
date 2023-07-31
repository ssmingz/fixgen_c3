class PlaceHold {
  Event sendEraseItemEvent(
      TableItem item, NMTTCUSTOMDRAW nmcd, int column, RECT cellRect, int hFont) {
    int nSavedDC = OS.SaveDC(nmcd.hdc);
    RECT insetRect = toolTipInset(cellRect);
    OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, null);
    GCData data = new GCData();
    data.device = display;
    data.foreground = OS.GetTextColor(nmcd.hdc);
    data.background = OS.GetBkColor(nmcd.hdc);
    data.font = Font.win32_new(display, hFont);
    GC gc = GC.win32_new(nmcd.hdc, data);
    Event event = new Event();
    event.item = item;
    event.index = column;
    event.gc = gc;
    event.detail |= SWT.FOREGROUND;
    event.x = cellRect.left;
    event.y = cellRect.top;
    event.width = cellRect.right - cellRect.left;
    event.height = cellRect.bottom - cellRect.top;
    sendEvent(EraseItem, event);
    event.gc = null;
    gc.dispose();
    OS.RestoreDC(nmcd.hdc, nSavedDC);
    return event;
  }
}
