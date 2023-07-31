class PlaceHold {
  LRESULT wmLButtonDown(int hwnd, int wParam, int lParam) {
    LRESULT result = null;
    int x = ((short) (lParam & 0xffff));
    int y = ((short) (lParam >> 16));
    boolean[] consume = null;
    boolean[] detect = null;
    boolean dragging = false;
    boolean mouseDown = true;
    if (((state & DRAG_DETECT) != 0) && hooks(DragDetect)) {
      if (!OS.IsWinCE) {
        detect = new boolean[1];
        consume = new boolean[1];
        dragging = dragDetect(hwnd, x, y, true, detect, consume);
        mouseDown = OS.GetKeyState(VK_LBUTTON) < 0;
      }
    }
    Display display = this.display;
    display.captureChanged = false;
    boolean dispatch = sendMouseEvent(MouseDown, 1, hwnd, WM_LBUTTONDOWN, wParam, lParam);
    if (dispatch && ((consume == null) || (!consume[0]))) {
      result = new LRESULT(callWindowProc(hwnd, WM_LBUTTONDOWN, wParam, lParam));
    } else {
      result = LRESULT.ZERO;
    }
    if (OS.IsPPC) {
      Menu menu = getMenu();
      boolean hasMenu = (menu != null) && (!menu.isDisposed());
      if (hasMenu || hooks(MenuDetect)) {
        SHRGINFO shrg = new SHRGINFO();
        shrg.cbSize = SHRGINFO.sizeof;
        shrg.hwndClient = hwnd;
        shrg.ptDown_x = x;
        shrg.ptDown_y = y;
        shrg.dwFlags = OS.SHRG_RETURNCMD;
        int type = OS.SHRecognizeGesture(shrg);
        if (type == OS.GN_CONTEXTMENU) {
          showMenu(x, y);
        }
      }
    }
    if (mouseDown) {
      if ((!display.captureChanged) && (!isDisposed())) {
        if (OS.GetCapture() != hwnd) {
          OS.SetCapture(hwnd);
        }
      }
    }
    if (dragging) {
      sendDragEvent(x, y);
    } else if ((detect != null) && detect[0]) {
      if (OS.GetKeyState(VK_ESCAPE) >= 0) {
        OS.SendMessage(hwnd, WM_LBUTTONUP, wParam, lParam);
      }
    }
    return result;
  }
}
