class PlaceHold {
  LRESULT WM_PARENTNOTIFY(int wParam, int lParam) {
    if (((state & CANVAS) != 0) && ((style & SWT.EMBEDDED) != 0)) {
      if ((wParam & 0xffff) == OS.WM_CREATE) {
        RECT rect = new RECT();
        OS.GetClientRect(handle, rect);
        resizeEmbeddedHandle(lParam, rect.right - rect.left, rect.bottom - rect.top);
      }
    }
    return super.WM_PARENTNOTIFY(wParam, lParam);
  }
}
