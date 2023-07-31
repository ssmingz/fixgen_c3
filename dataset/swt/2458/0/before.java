class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    if ((state & CANVAS) == 0) {
      return super.WM_PAINT(wParam, lParam);
    }
    int oldBits = 0;
    int newBits = 0;
    if (!OS.IsWinCE) {
      oldBits = OS.GetWindowLong(handle, GWL_STYLE);
      newBits = (oldBits | OS.WS_CLIPSIBLINGS) | OS.WS_CLIPCHILDREN;
      if (newBits != oldBits) {
        OS.SetWindowLong(handle, GWL_STYLE, newBits);
      }
    }
    PAINTSTRUCT ps = new PAINTSTRUCT();
    if (hooks(Paint)) {
      int[] lpRgnData = null;
      boolean isComplex = false;
      boolean exposeRegion = false;
      int rgn = 0;
      if ((style & (SWT.NO_MERGE_PAINTS | SWT.DOUBLE_BUFFERED)) != 0) {
        rgn = OS.CreateRectRgn(0, 0, 0, 0);
        isComplex = OS.GetUpdateRgn(handle, rgn, false) == OS.COMPLEXREGION;
      }
      if ((style & SWT.NO_MERGE_PAINTS) != 0) {
        if (isComplex) {
          int nBytes = OS.GetRegionData(rgn, 0, null);
          lpRgnData = new int[nBytes / 4];
          exposeRegion = OS.GetRegionData(rgn, nBytes, lpRgnData) != 0;
        }
      }
      GCData data = new GCData();
      data.ps = ps;
      data.hwnd = handle;
      GC gc = GC.win32_new(this, data);
      int width = ps.right - ps.left;
      int height = ps.bottom - ps.top;
      if ((width != 0) && (height != 0)) {
        GC paintGC = null;
        Image image = null;
        if ((style & SWT.DOUBLE_BUFFERED) != 0) {
          image = new Image(display, width, height);
          paintGC = gc;
          gc = new GC(image);
          gc.setForeground(getForeground());
          gc.setBackground(getBackground());
          gc.setFont(getFont());
          if ((style & SWT.NO_BACKGROUND) != 0) {
            paintGC.copyArea(image, ps.left, ps.top);
          } else {
            gc.fillRectangle(0, 0, width, height);
          }
          OS.OffsetRgn(rgn, -ps.left, -ps.top);
          OS.SelectClipRgn(gc.handle, rgn);
          OS.SetMetaRgn(gc.handle);
          OS.SetWindowOrgEx(gc.handle, ps.left, ps.top, null);
        }
        Event event = new Event();
        event.gc = gc;
        if (isComplex && exposeRegion) {
          RECT rect = new RECT();
          int count = lpRgnData[2];
          for (int i = 0; i < count; i++) {
            OS.SetRect(
                rect,
                lpRgnData[8 + (i << 2)],
                lpRgnData[(8 + (i << 2)) + 1],
                lpRgnData[(8 + (i << 2)) + 2],
                lpRgnData[(8 + (i << 2)) + 3]);
            if ((style & (SWT.DOUBLE_BUFFERED | SWT.NO_BACKGROUND)) == 0) {
              drawBackground(gc.handle, rect);
            }
            event.x = rect.left;
            event.y = rect.top;
            event.width = rect.right - rect.left;
            event.height = rect.bottom - rect.top;
            event.count = (count - 1) - i;
            sendEvent(Paint, event);
            if (isDisposed()) {
              break;
            }
          }
        } else {
          if ((style & (SWT.DOUBLE_BUFFERED | SWT.NO_BACKGROUND)) == 0) {
            RECT rect = new RECT();
            OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
            drawBackground(gc.handle, rect);
          }
          event.x = ps.left;
          event.y = ps.top;
          event.width = width;
          event.height = height;
          sendEvent(Paint, event);
        }
        event.gc = null;
        if ((style & SWT.DOUBLE_BUFFERED) != 0) {
          gc.dispose();
          if (!isDisposed()) {
            paintGC.drawImage(image, ps.left, ps.top);
          }
          image.dispose();
          gc = paintGC;
        }
      }
      gc.dispose();
      if (rgn != 0) {
        OS.DeleteObject(rgn);
      }
    } else {
      int hDC = OS.BeginPaint(handle, ps);
      if ((style & SWT.NO_BACKGROUND) == 0) {
        RECT rect = new RECT();
        OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
        drawBackground(hDC, rect);
      }
      OS.EndPaint(handle, ps);
    }
    if (!OS.IsWinCE) {
      if (newBits != oldBits) {
        if (!isDisposed()) {
          OS.SetWindowLong(handle, GWL_STYLE, oldBits);
        }
      }
    }
    return LRESULT.ZERO;
  }
}
