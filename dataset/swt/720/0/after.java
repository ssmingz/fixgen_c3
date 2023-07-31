class PlaceHold {
  LRESULT WM_KEYDOWN(int wParam, int lParam) {
    LRESULT result = super.WM_KEYDOWN(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((style & SWT.VERTICAL) != 0) {
      return result;
    }
    if ((style & SWT.MIRRORED) != 0) {
      switch (((int) (wParam))) {
        case OS.VK_LEFT:
        case OS.VK_RIGHT:
          {
            int key = (wParam == OS.VK_LEFT) ? OS.VK_RIGHT : OS.VK_LEFT;
            int code = callWindowProc(handle, WM_KEYDOWN, key, lParam);
            return new LRESULT(code);
          }
      }
    }
    return result;
  }
}
