class PlaceHold {
  LRESULT WM_PAINT(int wParam, int lParam) {
    if (((state & CANVAS) == 0) || ((state & FOREIGN_HANDLE) != 0)) {
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
      boolean bufferedPaint = false;
      if ((style & SWT.DOUBLE_BUFFERED) != 0) {
        if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
          if ((style & (SWT.NO_MERGE_PAINTS | SWT.RIGHT_TO_LEFT)) == 0) {
            if ((style & SWT.TRANSPARENT) == 0) {
              bufferedPaint = true;
            }
          }
        }
      }
      if (bufferedPaint) {
        int hDC = OS.BeginPaint(handle, ps);
        int width = ps.right - ps.left;
        int height = ps.bottom - ps.top;
        if ((width != 0) && (height != 0)) {
          int[] phdc = new int[1];
          int flags = OS.BPBF_COMPATIBLEBITMAP;
          RECT prcTarget = new RECT();
          OS.SetRect(prcTarget, ps.left, ps.top, ps.right, ps.bottom);
          int hBufferedPaint = OS.BeginBufferedPaint(hDC, prcTarget, flags, null, phdc);
          GCData data = new GCData();
          data.device = display;
          data.foreground = getForegroundPixel();
          Control control = findBackgroundControl();
          if (control == null) {
            control = this;
          }
          data.background = control.getBackgroundPixel();
          data.font = Font.win32_new(display, OS.SendMessage(handle, WM_GETFONT, 0, 0));
          data.uiState = ((int) (OS.SendMessage(handle, WM_QUERYUISTATE, 0, 0)));
          if ((style & SWT.NO_BACKGROUND) != 0) {
          } else {
            RECT rect = new RECT();
            OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
            drawBackground(phdc[0], rect);
          }
          GC gc = GC.win32_new(phdc[0], data);
          Event event = new Event();
          event.gc = gc;
          event.x = ps.left;
          event.y = ps.top;
          event.width = width;
          event.height = height;
          sendEvent(Paint, event);
          if (data.focusDrawn && (!isDisposed())) {
            updateUIState();
          }
          gc.dispose();
          OS.EndBufferedPaint(hBufferedPaint, true);
        }
        OS.EndPaint(handle, ps);
      } else {
        GCData data = new GCData();
        data.ps = ps;
        data.hwnd = handle;
        GC gc = GC.win32_new(this, data);
        int sysRgn = 0;
        if (((style & (SWT.DOUBLE_BUFFERED | SWT.TRANSPARENT)) != 0)
            || ((style & SWT.NO_MERGE_PAINTS) != 0)) {
          sysRgn = OS.CreateRectRgn(0, 0, 0, 0);
          if (OS.GetRandomRgn(gc.handle, sysRgn, SYSRGN) == 1) {
            if (OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
              if ((OS.GetLayout(gc.handle) & OS.LAYOUT_RTL) != 0) {
                int nBytes = OS.GetRegionData(sysRgn, 0, null);
                int[] lpRgnData = new int[nBytes / 4];
                OS.GetRegionData(sysRgn, nBytes, lpRgnData);
                int newSysRgn =
                    OS.ExtCreateRegion(new float[] {-1, 0, 0, 1, 0, 0}, nBytes, lpRgnData);
                OS.DeleteObject(sysRgn);
                sysRgn = newSysRgn;
              }
            }
            if (OS.IsWinNT) {
              POINT pt = new POINT();
              OS.MapWindowPoints(0, handle, pt, 1);
              OS.OffsetRgn(sysRgn, pt.x, pt.y);
            }
          }
        }
        int width = ps.right - ps.left;
        int height = ps.bottom - ps.top;
        if ((width != 0) && (height != 0)) {
          GC paintGC = null;
          Image image = null;
          if ((style & (SWT.DOUBLE_BUFFERED | SWT.TRANSPARENT)) != 0) {
            image = new Image(display, width, height);
            paintGC = gc;
            gc = new GC(image, paintGC.getStyle() & SWT.RIGHT_TO_LEFT);
            GCData gcData = gc.getGCData();
            gcData.uiState = data.uiState;
            gc.setForeground(getForeground());
            gc.setBackground(getBackground());
            gc.setFont(getFont());
            if ((style & SWT.TRANSPARENT) != 0) {
              OS.BitBlt(gc.handle, 0, 0, width, height, paintGC.handle, ps.left, ps.top, SRCCOPY);
            }
            OS.OffsetRgn(sysRgn, -ps.left, -ps.top);
            OS.SelectClipRgn(gc.handle, sysRgn);
            OS.OffsetRgn(sysRgn, ps.left, ps.top);
            OS.SetMetaRgn(gc.handle);
            OS.SetWindowOrgEx(gc.handle, ps.left, ps.top, null);
            OS.SetBrushOrgEx(gc.handle, ps.left, ps.top, null);
            if ((style & (SWT.NO_BACKGROUND | SWT.TRANSPARENT)) != 0) {
            } else {
              RECT rect = new RECT();
              OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
              drawBackground(gc.handle, rect);
            }
          }
          Event event = new Event();
          event.gc = gc;
          RECT rect = null;
          if (((style & SWT.NO_MERGE_PAINTS) != 0)
              && (OS.GetRgnBox(sysRgn, rect = new RECT()) == OS.COMPLEXREGION)) {
            int nBytes = OS.GetRegionData(sysRgn, 0, null);
            int[] lpRgnData = new int[nBytes / 4];
            OS.GetRegionData(sysRgn, nBytes, lpRgnData);
            int count = lpRgnData[2];
            for (int i = 0; i < count; i++) {
              int offset = 8 + (i << 2);
              OS.SetRect(
                  rect,
                  lpRgnData[offset],
                  lpRgnData[offset + 1],
                  lpRgnData[offset + 2],
                  lpRgnData[offset + 3]);
              if ((style & ((SWT.DOUBLE_BUFFERED | SWT.NO_BACKGROUND) | SWT.TRANSPARENT)) == 0) {
                drawBackground(gc.handle, rect);
              }
              event.x = rect.left;
              event.y = rect.top;
              event.width = rect.right - rect.left;
              event.height = rect.bottom - rect.top;
              event.count = (count - 1) - i;
              sendEvent(Paint, event);
            }
          } else {
            if ((style & ((SWT.DOUBLE_BUFFERED | SWT.NO_BACKGROUND) | SWT.TRANSPARENT)) == 0) {
              if (rect == null) {
                rect = new RECT();
              }
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
          if ((style & (SWT.DOUBLE_BUFFERED | SWT.TRANSPARENT)) != 0) {
            if (!gc.isDisposed()) {
              GCData gcData = gc.getGCData();
              if (gcData.focusDrawn && (!isDisposed())) {
                updateUIState();
              }
            }
            gc.dispose();
            if (!isDisposed()) {
              paintGC.drawImage(image, ps.left, ps.top);
            }
            image.dispose();
            gc = paintGC;
          }
        }
        if (sysRgn != 0) {
          OS.DeleteObject(sysRgn);
        }
        if (data.focusDrawn && (!isDisposed())) {
          updateUIState();
        }
        gc.dispose();
      }
    } else {
      int hDC = OS.BeginPaint(handle, ps);
      if ((style & (SWT.NO_BACKGROUND | SWT.TRANSPARENT)) == 0) {
        RECT rect = new RECT();
        OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
        drawBackground(hDC, rect);
      }
      OS.EndPaint(handle, ps);
    }
    if ((!OS.IsWinCE) && (!isDisposed())) {
      if (newBits != oldBits) {
        if (!isDisposed()) {
          OS.SetWindowLong(handle, GWL_STYLE, oldBits);
        }
      }
    }
    return LRESULT.ZERO;
  }
}
