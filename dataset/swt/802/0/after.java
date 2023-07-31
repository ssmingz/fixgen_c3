class PlaceHold {
  boolean translateTraversal(MSG msg) {
    if ((state & CANVAS) != 0) {
      if ((style & SWT.EMBEDDED) != 0) {
        return false;
      }
      switch (((int) (msg.wParam))) {
        case OS.VK_UP:
        case OS.VK_LEFT:
        case OS.VK_DOWN:
        case OS.VK_RIGHT:
        case OS.VK_PRIOR:
        case OS.VK_NEXT:
          OS.SendMessage(msg.hwnd, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
          break;
      }
    }
    return super.translateTraversal(msg);
  }
}
