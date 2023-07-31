class PlaceHold {
  LRESULT WM_WINDOWPOSCHANGING(int wParam, int lParam) {
    LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (ignoreResize) {
      return result;
    }
    if (drawCount != 0) {
      return result;
    }
    if ((style & SWT.WRAP) == 0) {
      return result;
    }
    if (!OS.IsWindowVisible(handle)) {
      return result;
    }
    if (OS.SendMessage(handle, TB_GETROWS, 0, 0) == 1) {
      return result;
    }
    WINDOWPOS lpwp = new WINDOWPOS();
    OS.MoveMemory(lpwp, lParam, sizeof);
    if ((lpwp.flags & (OS.SWP_NOSIZE | OS.SWP_NOREDRAW)) != 0) {
      return result;
    }
    RECT oldRect = new RECT();
    OS.GetClientRect(handle, oldRect);
    RECT newRect = new RECT();
    OS.SetRect(newRect, 0, 0, lpwp.cx, lpwp.cy);
    OS.SendMessage(handle, WM_NCCALCSIZE, 0, newRect);
    int oldWidth = oldRect.right - oldRect.left;
    int newWidth = newRect.right - newRect.left;
    if (newWidth > oldWidth) {
      RECT rect = new RECT();
      int newHeight = newRect.bottom - newRect.top;
      OS.SetRect(rect, oldWidth - 2, 0, oldWidth, newHeight);
      OS.InvalidateRect(handle, rect, false);
    }
    return result;
  }
}
