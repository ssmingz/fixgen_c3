class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    if (!ignoreShrink) {
      int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
      if ((items.length > 4) && ((items.length - count) > 3)) {
        int length = Math.max(4, ((count + 3) / 4) * 4);
        TableItem[] newItems = new TableItem[length];
        System.arraycopy(items, 0, newItems, 0, count);
        items = newItems;
      }
    }
    if (fixScrollWidth) {
      setScrollWidth(null, true);
    }
    if (OS.COMCTL32_MAJOR < 6) {
      if (((style & SWT.DOUBLE_BUFFERED) != 0) || (findImageControl() != null)) {
        int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
        if ((bits & OS.LVS_EX_DOUBLEBUFFER) == 0) {
          GC gc = null;
          int paintDC = 0;
          PAINTSTRUCT ps = new PAINTSTRUCT();
          boolean hooksPaint = hooks(Paint);
          if (hooksPaint) {
            GCData data = new GCData();
            data.ps = ps;
            data.hwnd = handle;
            gc = GC.win32_new(this, data);
            paintDC = gc.handle;
          } else {
            paintDC = OS.BeginPaint(handle, ps);
          }
          int width = ps.right - ps.left;
          int height = ps.bottom - ps.top;
          if ((width != 0) && (height != 0)) {
            int hDC = OS.CreateCompatibleDC(paintDC);
            POINT lpPoint1 = new POINT();
            POINT lpPoint2 = new POINT();
            OS.SetWindowOrgEx(hDC, ps.left, ps.top, lpPoint1);
            OS.SetBrushOrgEx(hDC, ps.left, ps.top, lpPoint2);
            int hBitmap = OS.CreateCompatibleBitmap(paintDC, width, height);
            int hOldBitmap = OS.SelectObject(hDC, hBitmap);
            if (((int) (OS.SendMessage(handle, LVM_GETBKCOLOR, 0, 0))) != OS.CLR_NONE) {
              RECT rect = new RECT();
              OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
              drawBackground(hDC, rect);
            }
            callWindowProc(handle, WM_PAINT, hDC, 0);
            OS.SetWindowOrgEx(hDC, lpPoint1.x, lpPoint1.y, null);
            OS.SetBrushOrgEx(hDC, lpPoint2.x, lpPoint2.y, null);
            OS.BitBlt(paintDC, ps.left, ps.top, width, height, hDC, 0, 0, SRCCOPY);
            OS.SelectObject(hDC, hOldBitmap);
            OS.DeleteObject(hBitmap);
            OS.DeleteObject(hDC);
            if (hooksPaint) {
              Event event = new Event();
              event.gc = gc;
              event.x = ps.left;
              event.y = ps.top;
              event.width = ps.right - ps.left;
              event.height = ps.bottom - ps.top;
              sendEvent(Paint, event);
              event.gc = null;
            }
          }
          if (hooksPaint) {
            gc.dispose();
          } else {
            OS.EndPaint(handle, ps);
          }
          return LRESULT.ZERO;
        }
      }
    }
    return super.WM_PAINT(wParam, lParam);
  }
}
