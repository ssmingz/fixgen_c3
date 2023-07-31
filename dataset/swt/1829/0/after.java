class PlaceHold {
  int hit(Theme theme, Point position, Rectangle bounds) {
    if (!((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed())) {
      return DrawData.WIDGET_NOWHERE;
    }
    if (!bounds.contains(position)) {
      return DrawData.WIDGET_NOWHERE;
    }
    long hTheme = OS.OpenThemeData(0, EDIT);
    int[] part = getPartId(WIDGET_WHOLE);
    int iPartId = part[0];
    int iStateId = part[1];
    RECT rect = new RECT();
    rect.left = bounds.x;
    rect.right = bounds.x + bounds.width;
    rect.top = bounds.y;
    rect.bottom = bounds.y + bounds.height;
    RECT contentRect = new RECT();
    OS.GetThemeBackgroundContentRect(hTheme, 0, iPartId, iStateId, rect, contentRect);
    OS.CloseThemeData(hTheme);
    hTheme = OS.OpenThemeData(0, getClassId());
    int width = OS.GetThemeSysSize(hTheme, SM_CXVSCROLL);
    OS.CloseThemeData(hTheme);
    Rectangle arrowRect =
        new Rectangle(
            contentRect.right - width,
            contentRect.top,
            contentRect.bottom - contentRect.top,
            width);
    if (arrowRect.contains(position)) {
      return DrawData.COMBO_ARROW;
    }
    return DrawData.WIDGET_WHOLE;
  }
}
