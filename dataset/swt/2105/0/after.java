class PlaceHold {
  LRESULT WM_SYSCOMMAND(int wParam, int lParam) {
    LRESULT result = super.WM_SYSCOMMAND(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((wParam & 0xf000) == 0) {
      return result;
    }
    if (!OS.IsWinCE) {
      int cmd = ((int) (wParam)) & 0xfff0;
      switch (cmd) {
        case OS.SC_HSCROLL:
        case OS.SC_VSCROLL:
          boolean showHBar = (horizontalBar != null) && horizontalBar.getVisible();
          boolean showVBar = (verticalBar != null) && verticalBar.getVisible();
          int code = callWindowProc(handle, WM_SYSCOMMAND, wParam, lParam);
          if ((showHBar != ((horizontalBar != null) && horizontalBar.getVisible()))
              || (showVBar != ((verticalBar != null) && verticalBar.getVisible()))) {
            int flags = (OS.RDW_FRAME | OS.RDW_INVALIDATE) | OS.RDW_UPDATENOW;
            OS.RedrawWindow(handle, null, 0, flags);
          }
          if (code == 0) {
            return LRESULT.ZERO;
          }
          return new LRESULT(code);
      }
    }
    return result;
  }
}
