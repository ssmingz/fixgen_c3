class PlaceHold {
  LRESULT WM_LBUTTONDOWN(int wParam, int lParam) {
    boolean dragging = false;
    boolean mouseDown = true;
    boolean dragDetect = hooks(DragDetect);
    if (dragDetect) {
      if (!OS.IsWinCE) {
        POINT pt = new POINT();
        pt.x = ((short) (lParam & 0xffff));
        pt.y = ((short) (lParam >> 16));
        OS.ClientToScreen(handle, pt);
        dragging = OS.DragDetect(handle, pt);
        mouseDown = OS.GetKeyState(VK_LBUTTON) < 0;
      }
    }
    sendMouseEvent(MouseDown, 1, WM_LBUTTONDOWN, wParam, lParam);
    int result = callWindowProc(WM_LBUTTONDOWN, wParam, lParam);
    if (OS.IsPPC) {
      boolean hasMenu = (menu != null) && (!menu.isDisposed());
      if (hasMenu || hooks(MenuDetect)) {
        int x = ((short) (lParam & 0xffff));
        int y = ((short) (lParam >> 16));
        SHRGINFO shrg = new SHRGINFO();
        shrg.cbSize = SHRGINFO.sizeof;
        shrg.hwndClient = handle;
        shrg.ptDown_x = x;
        shrg.ptDown_y = y;
        shrg.dwFlags = OS.SHRG_RETURNCMD;
        int type = OS.SHRecognizeGesture(shrg);
        if (type == OS.GN_CONTEXTMENU) {
          showMenu(x, y, false);
        }
      }
    }
    if (mouseDown) {
      if (OS.GetCapture() != handle) {
        OS.SetCapture(handle);
      }
    }
    if (dragging) {
      Event event = new Event();
      event.x = ((short) (lParam & 0xffff));
      event.y = ((short) (lParam >> 16));
      postEvent(DragDetect, event);
    } else if (dragDetect) {
      if (OS.GetKeyState(VK_ESCAPE) >= 0) {
        OS.SendMessage(handle, WM_LBUTTONUP, wParam, lParam);
      }
    }
    return new LRESULT(result);
  }
}
