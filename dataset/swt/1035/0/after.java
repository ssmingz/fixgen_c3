class PlaceHold {
  LRESULT WM_SIZE(int wParam, int lParam) {
    if (ignoreResize) {
      int code = callWindowProc(handle, WM_SIZE, wParam, lParam);
      if (code == 0) {
        return LRESULT.ZERO;
      }
      return new LRESULT(code);
    }
    RECT[] rects = null;
    int rgn = 0;
    int oldCount = 0;
    boolean fixRedraw =
        (((drawCount == 0) && ((style & SWT.WRAP) != 0)) && OS.IsWindowVisible(handle))
            && (OS.SendMessage(handle, TB_GETROWS, 0, 0) != 1);
    if (fixRedraw) {
      rgn = OS.CreateRectRgn(0, 0, 0, 0);
      OS.GetUpdateRgn(handle, rgn, false);
      oldCount = OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0);
      rects = new RECT[oldCount];
      for (int i = 0; i < oldCount; i++) {
        rects[i] = new RECT();
        OS.SendMessage(handle, TB_GETITEMRECT, i, rects[i]);
      }
    }
    LRESULT result = super.WM_SIZE(wParam, lParam);
    if (isDisposed()) {
      return result;
    }
    if (fixRedraw) {
      int newCount = OS.SendMessage(handle, TB_BUTTONCOUNT, 0, 0);
      if (newCount == oldCount) {
        int index = 0;
        RECT rect = new RECT();
        while (index < newCount) {
          OS.SendMessage(handle, TB_GETITEMRECT, index, rect);
          if (!OS.EqualRect(rects[index], rect)) {
            break;
          }
          index++;
        }
        if (index == newCount) {
          OS.ValidateRect(handle, null);
          OS.InvalidateRgn(handle, rgn, false);
        }
      }
      OS.DeleteObject(rgn);
    }
    layoutItems();
    return result;
  }
}
