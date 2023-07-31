class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    if (OS.COMCTL32_MAJOR >= 6) {
      return super.WM_PAINT(wParam, lParam);
    }
    PAINTSTRUCT ps = new PAINTSTRUCT();
    GCData data = new GCData();
    data.ps = ps;
    data.hwnd = handle;
    GC gc = new_GC(data);
    if (gc != null) {
      int width = ps.right - ps.left;
      int height = ps.bottom - ps.top;
      if ((width != 0) && (height != 0)) {
        RECT rect = new RECT();
        OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
        drawBackground(gc.handle, rect);
        int selStart = selection.x;
        int selEnd = selection.y;
        if (selStart > selEnd) {
          selStart = selection.y;
          selEnd = selection.x;
        }
        layout.draw(gc, 0, 0, selStart, selEnd, null, null);
        if (hasFocus() && (focusIndex != (-1))) {
          Rectangle[] rects = getRectangles(focusIndex);
          for (int i = 0; i < rects.length; i++) {
            Rectangle rectangle = rects[i];
            gc.drawFocus(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
          }
        }
        if (hooks(Paint) || filters(Paint)) {
          Event event = new Event();
          event.gc = gc;
          event.x = ps.left;
          event.y = ps.top;
          event.width = width;
          event.height = height;
          sendEvent(Paint, event);
          event.gc = null;
        }
      }
      gc.dispose();
    }
    return LRESULT.ZERO;
  }
}
