class PlaceHold {
  void drawWidget(GC gc, RECT clipRect) {
    int hTheme = 0;
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      hTheme = OS.OpenThemeData(handle, EXPLORERBAR);
    }
    if (hTheme != 0) {
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      OS.DrawThemeBackground(hTheme, gc.handle, EBP_HEADERBACKGROUND, 0, rect, clipRect);
    } else {
      drawBackground(gc.handle);
    }
    boolean drawFocus = false;
    if (handle == OS.GetFocus()) {
      int uiState = OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0);
      drawFocus = (uiState & OS.UISF_HIDEFOCUS) == 0;
    }
    for (int i = 0; i < itemCount; i++) {
      ExpandItem item = items[i];
      item.drawItem(gc, hTheme, clipRect, (i == focusIndex) && drawFocus);
    }
    if (hTheme != 0) {
      OS.CloseThemeData(hTheme);
    }
  }
}
