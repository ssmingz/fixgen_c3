class PlaceHold {
  int transparentProc(int hwnd, int msg, int wParam, int lParam) {
    switch (((int) (msg))) {
      case OS.WM_NCHITTEST:
        if (inEvent) {
          return OS.HTTRANSPARENT;
        }
        break;
      case OS.WM_SETCURSOR:
        if (clientCursor != 0) {
          OS.SetCursor(clientCursor);
          return 1;
        }
        if (resizeCursor != 0) {
          OS.SetCursor(resizeCursor);
          return 1;
        }
        break;
      case OS.WM_PAINT:
        if (((parent == null) && (!OS.IsWinCE)) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
          PAINTSTRUCT ps = new PAINTSTRUCT();
          int hDC = OS.BeginPaint(hwnd, ps);
          int hBitmap = 0;
          int hBrush = 0;
          int oldBrush = 0;
          int transparentBrush = OS.CreateSolidBrush(0xffffff);
          oldBrush = OS.SelectObject(hDC, transparentBrush);
          OS.PatBlt(hDC, ps.left, ps.top, ps.right - ps.left, ps.bottom - ps.top, PATCOPY);
          OS.SelectObject(hDC, oldBrush);
          OS.DeleteObject(transparentBrush);
          int bandWidth = 1;
          if (stippled) {
            bandWidth = 3;
            byte[] bits = new byte[] {-86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0};
            hBitmap = OS.CreateBitmap(8, 8, 1, 1, bits);
            hBrush = OS.CreatePatternBrush(hBitmap);
            oldBrush = OS.SelectObject(hDC, hBrush);
            OS.SetBkColor(hDC, 0xf0f0f0);
          } else {
            oldBrush = OS.SelectObject(hDC, OS.GetStockObject(BLACK_BRUSH));
          }
          Rectangle[] rects = this.rectangles;
          for (int i = 0; i < rects.length; i++) {
            Rectangle rect = rects[i];
            OS.PatBlt(hDC, rect.x, rect.y, rect.width, bandWidth, PATCOPY);
            OS.PatBlt(
                hDC, rect.x, rect.y + bandWidth, bandWidth, rect.height - (bandWidth * 2), PATCOPY);
            OS.PatBlt(
                hDC,
                (rect.x + rect.width) - bandWidth,
                rect.y + bandWidth,
                bandWidth,
                rect.height - (bandWidth * 2),
                PATCOPY);
            OS.PatBlt(
                hDC, rect.x, (rect.y + rect.height) - bandWidth, rect.width, bandWidth, PATCOPY);
          }
          OS.SelectObject(hDC, oldBrush);
          if (stippled) {
            OS.DeleteObject(hBrush);
            OS.DeleteObject(hBitmap);
          }
          OS.EndPaint(hwnd, ps);
          return 0;
        }
    }
    return OS.CallWindowProc(oldProc, hwnd, ((int) (msg)), wParam, lParam);
  }
}
