class PlaceHold {
  public boolean open() {
    checkWidget();
    cancelled = false;
    tracking = true;
    int vStyle = style & (SWT.UP | SWT.DOWN);
    if ((vStyle == SWT.UP) || (vStyle == SWT.DOWN)) {
      cursorOrientation |= vStyle;
    }
    int hStyle = style & (SWT.LEFT | SWT.RIGHT);
    if ((hStyle == SWT.LEFT) || (hStyle == SWT.RIGHT)) {
      cursorOrientation |= hStyle;
    }
    Callback newProc = null;
    boolean mouseDown = OS.GetKeyState(VK_LBUTTON) < 0;
    if (IsVista && (parent == null)) {
      Rectangle bounds = display.getBounds();
      hwndTransparent =
          OS.CreateWindowEx(
              (OS.WS_EX_LAYERED | OS.WS_EX_NOACTIVATE) | OS.WS_EX_TOOLWINDOW,
              windowClass,
              null,
              WS_POPUP,
              bounds.x,
              bounds.y,
              bounds.width,
              bounds.height,
              0,
              0,
              OS.GetModuleHandle(null),
              null);
      OS.SetLayeredWindowAttributes(hwndTransparent, 0, ((byte) (0x1)), LWA_ALPHA);
      hwndOpaque =
          OS.CreateWindowEx(
              (OS.WS_EX_LAYERED | OS.WS_EX_NOACTIVATE) | OS.WS_EX_TOOLWINDOW,
              windowClass,
              null,
              WS_POPUP,
              bounds.x,
              bounds.y,
              bounds.width,
              bounds.height,
              hwndTransparent,
              0,
              OS.GetModuleHandle(null),
              null);
      OS.SetLayeredWindowAttributes(
          hwndOpaque, 0xffffff, ((byte) (0)), OS.LWA_COLORKEY | OS.LWA_ALPHA);
      drawn = false;
      newProc = new Callback(this, "transparentProc", 4);
      long newProcAddress = newProc.getAddress();
      if (newProcAddress == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      oldTransparentProc = OS.GetWindowLongPtr(hwndTransparent, GWLP_WNDPROC);
      OS.SetWindowLongPtr(hwndTransparent, GWLP_WNDPROC, newProcAddress);
      oldOpaqueProc = OS.GetWindowLongPtr(hwndOpaque, GWLP_WNDPROC);
      OS.SetWindowLongPtr(hwndOpaque, GWLP_WNDPROC, newProcAddress);
      OS.ShowWindow(hwndTransparent, SW_SHOWNOACTIVATE);
      OS.ShowWindow(hwndOpaque, SW_SHOWNOACTIVATE);
    } else if (!mouseDown) {
      Rectangle bounds = display.getBounds();
      hwndTransparent =
          OS.CreateWindowEx(
              WS_EX_TRANSPARENT,
              windowClass,
              null,
              WS_POPUP,
              bounds.x,
              bounds.y,
              bounds.width,
              bounds.height,
              0,
              0,
              OS.GetModuleHandle(null),
              null);
      newProc = new Callback(this, "transparentProc", 4);
      long newProcAddress = newProc.getAddress();
      if (newProcAddress == 0) {
        error(ERROR_NO_MORE_CALLBACKS);
      }
      oldTransparentProc = OS.GetWindowLongPtr(hwndTransparent, GWLP_WNDPROC);
      OS.SetWindowLongPtr(hwndTransparent, GWLP_WNDPROC, newProcAddress);
      OS.ShowWindow(hwndTransparent, SW_SHOWNOACTIVATE);
    }
    update();
    drawRectangles(rectangles, stippled);
    Point cursorPos = null;
    if (mouseDown) {
      POINT pt = new POINT();
      OS.GetCursorPos(pt);
      cursorPos = new Point(pt.x, pt.y);
    } else if ((style & SWT.RESIZE) != 0) {
      cursorPos = adjustResizeCursor();
    } else {
      cursorPos = adjustMoveCursor();
    }
    if (cursorPos != null) {
      oldX = cursorPos.x;
      oldY = cursorPos.y;
    }
    Display display = this.display;
    try {
      MSG msg = new MSG();
      while (tracking && (!cancelled)) {
        if ((parent != null) && parent.isDisposed()) {
          break;
        }
        display.runSkin();
        display.runDeferredLayouts();
        OS.GetMessage(msg, 0, 0, 0);
        OS.TranslateMessage(msg);
        switch (msg.message) {
          case OS.WM_LBUTTONUP:
          case OS.WM_MOUSEMOVE:
            wmMouse(msg.message, msg.wParam, msg.lParam);
            break;
          case OS.WM_IME_CHAR:
            wmIMEChar(msg.hwnd, msg.wParam, msg.lParam);
            break;
          case OS.WM_CHAR:
            wmChar(msg.hwnd, msg.wParam, msg.lParam);
            break;
          case OS.WM_KEYDOWN:
            wmKeyDown(msg.hwnd, msg.wParam, msg.lParam);
            break;
          case OS.WM_KEYUP:
            wmKeyUp(msg.hwnd, msg.wParam, msg.lParam);
            break;
          case OS.WM_SYSCHAR:
            wmSysChar(msg.hwnd, msg.wParam, msg.lParam);
            break;
          case OS.WM_SYSKEYDOWN:
            wmSysKeyDown(msg.hwnd, msg.wParam, msg.lParam);
            break;
          case OS.WM_SYSKEYUP:
            wmSysKeyUp(msg.hwnd, msg.wParam, msg.lParam);
            break;
        }
        if ((OS.WM_KEYFIRST <= msg.message) && (msg.message <= OS.WM_KEYLAST)) {
          continue;
        }
        if ((OS.WM_MOUSEFIRST <= msg.message) && (msg.message <= OS.WM_MOUSELAST)) {
          continue;
        }
        if (hwndOpaque == 0) {
          if (msg.message == OS.WM_PAINT) {
            update();
            drawRectangles(rectangles, stippled);
          }
        }
        OS.DispatchMessage(msg);
        if (hwndOpaque == 0) {
          if (msg.message == OS.WM_PAINT) {
            drawRectangles(rectangles, stippled);
          }
        }
        display.runAsyncMessages(false);
      }
      if (mouseDown) {
        OS.ReleaseCapture();
      }
      if (!isDisposed()) {
        update();
        drawRectangles(rectangles, stippled);
      }
    } finally {
      if (hwndTransparent != 0) {
        OS.DestroyWindow(hwndTransparent);
        hwndTransparent = 0;
      }
      hwndOpaque = 0;
      if (newProc != null) {
        newProc.dispose();
        oldTransparentProc = oldOpaqueProc = 0;
      }
      if (resizeCursor != 0) {
        OS.DestroyCursor(resizeCursor);
        resizeCursor = 0;
      }
    }
    tracking = false;
    return !cancelled;
  }
}
