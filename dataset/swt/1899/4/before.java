class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    if (shrink) {
      int count = items.length - 1;
      while (count >= 0) {
        if (items[count] != null) {
          break;
        }
        --count;
      }
      count++;
      if ((items.length > 4) && ((items.length - count) > 3)) {
        int length = Math.max(4, ((count + 3) / 4) * 4);
        TreeItem[] newItems = new TreeItem[length];
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
      }
      shrink = false;
    }
    if (((style & SWT.DOUBLE_BUFFERED) != 0) || (findImageControl() != null)) {
      GC gc = null;
      int paintDC = 0;
      PAINTSTRUCT ps = new PAINTSTRUCT();
      if (hooks(Paint)) {
        GCData data = new GCData();
        data.ps = ps;
        data.hwnd = handle;
        gc = GC.win32_new(this, data);
        paintDC = gc.handle;
      } else {
        paintDC = OS.BeginPaint(handle, ps);
      }
      RECT rect = new RECT();
      OS.GetClientRect(handle, rect);
      int x = rect.left;
      int y = rect.top;
      int width = rect.right - rect.left;
      int height = rect.bottom - rect.top;
      int hDC = OS.CreateCompatibleDC(paintDC);
      int hBitmap = OS.CreateCompatibleBitmap(paintDC, width, height);
      int hOldBitmap = OS.SelectObject(hDC, hBitmap);
      drawBackground(hDC, rect);
      int code = callWindowProc(handle, WM_PAINT, hDC, 0);
      OS.BitBlt(paintDC, x, y, width, height, hDC, 0, 0, SRCCOPY);
      OS.SelectObject(hDC, hOldBitmap);
      OS.DeleteObject(hBitmap);
      OS.DeleteObject(hDC);
      if (hooks(Paint)) {
        Event event = new Event();
        event.gc = gc;
        event.x = ps.left;
        event.y = ps.top;
        event.width = ps.right - ps.left;
        event.height = ps.bottom - ps.top;
        sendEvent(Paint, event);
        event.gc = null;
        gc.dispose();
      } else {
        OS.EndPaint(handle, ps);
      }
      return new LRESULT(code);
    }
    return super.WM_PAINT(wParam, lParam);
  }
}
