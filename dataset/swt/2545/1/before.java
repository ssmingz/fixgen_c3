class PlaceHold {
  LRESULT WM_MOUSEWHEEL(int wParam, int lParam) {
    LRESULT result = super.WM_MOUSEWHEEL(wParam, lParam);
    if (result != null) {
      return result;
    }
    if ((state & CANVAS) != 0) {
      if ((wParam & (OS.MK_SHIFT | OS.MK_CONTROL)) != 0) {
        return result;
      }
      boolean vertical = (verticalBar != null) && verticalBar.getEnabled();
      boolean horizontal = (horizontalBar != null) && horizontalBar.getEnabled();
      int msg = (vertical) ? OS.WM_VSCROLL : horizontal ? OS.WM_HSCROLL : 0;
      if (msg == 0) {
        return result;
      }
      int[] value = new int[1];
      OS.SystemParametersInfo(SPI_GETWHEELSCROLLLINES, 0, value, 0);
      int delta = ((short) (wParam >> 16));
      int code = 0;
      int count = 0;
      if (value[0] == OS.WHEEL_PAGESCROLL) {
        code = (delta < 0) ? OS.SB_PAGEDOWN : OS.SB_PAGEUP;
        count = 1;
      } else {
        code = (delta < 0) ? OS.SB_LINEDOWN : OS.SB_LINEUP;
        delta = Math.abs(delta);
        if (delta < OS.WHEEL_DELTA) {
          return result;
        }
        if (msg == OS.WM_VSCROLL) {
          count = (value[0] * delta) / OS.WHEEL_DELTA;
        } else {
          count = delta / OS.WHEEL_DELTA;
        }
      }
      for (int i = 0; i < count; i++) {
        OS.SendMessage(handle, msg, code, 0);
      }
      return LRESULT.ZERO;
    }
    int vPosition = (verticalBar == null) ? 0 : verticalBar.getSelection();
    int hPosition = (horizontalBar == null) ? 0 : horizontalBar.getSelection();
    int code = callWindowProc(WM_MOUSEWHEEL, wParam, lParam);
    if (verticalBar != null) {
      int position = verticalBar.getSelection();
      if (position != vPosition) {
        Event event = new Event();
        event.detail = (position < vPosition) ? SWT.PAGE_UP : SWT.PAGE_DOWN;
        verticalBar.sendEvent(Selection, event);
      }
    }
    if (horizontalBar != null) {
      int position = horizontalBar.getSelection();
      if (position != hPosition) {
        Event event = new Event();
        event.detail = (position < hPosition) ? SWT.PAGE_UP : SWT.PAGE_DOWN;
        horizontalBar.sendEvent(Selection, event);
      }
    }
    return new LRESULT(code);
  }
}
