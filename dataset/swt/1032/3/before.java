class PlaceHold {
  LRESULT WM_WINDOWPOSCHANGING(int wParam, int lParam) {
    LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (drawCount != 0) {
      return result;
    }
    if (!OS.IsWindowVisible(handle)) {
      return result;
    }
    if (ignoreResize) {
      WINDOWPOS lpwp = new WINDOWPOS();
      OS.MoveMemory(lpwp, lParam, sizeof);
      if ((lpwp.flags & OS.SWP_NOSIZE) == 0) {
        lpwp.flags |= OS.SWP_NOREDRAW;
        OS.MoveMemory(lParam, lpwp, sizeof);
        OS.InvalidateRect(handle, null, true);
        RECT rect = new RECT();
        OS.GetWindowRect(handle, rect);
        int width = rect.right - rect.left;
        int height = rect.bottom - rect.top;
        if ((width != 0) && (height != 0)) {
          int hwndParent = parent.handle;
          int hwndChild = OS.GetWindow(hwndParent, GW_CHILD);
          OS.MapWindowPoints(0, hwndParent, rect, 2);
          int rgn1 = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
          while (hwndChild != 0) {
            if (hwndChild != handle) {
              OS.GetWindowRect(hwndChild, rect);
              OS.MapWindowPoints(0, hwndParent, rect, 2);
              int rgn2 = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
              OS.CombineRgn(rgn1, rgn1, rgn2, RGN_DIFF);
              OS.DeleteObject(rgn2);
            }
            hwndChild = OS.GetWindow(hwndChild, GW_HWNDNEXT);
          }
          int flags = (OS.RDW_ERASE | OS.RDW_FRAME) | OS.RDW_INVALIDATE;
          OS.RedrawWindow(hwndParent, null, rgn1, flags);
          OS.DeleteObject(rgn1);
        }
      }
    }
    return result;
  }
}
