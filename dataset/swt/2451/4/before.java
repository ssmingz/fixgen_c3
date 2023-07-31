class PlaceHold {
  LRESULT WM_CHAR(int wParam, int lParam) {
    LRESULT result = super.WM_CHAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    if (OS.COMCTL32_MAJOR < 6) {
      if (focusIndex == (-1)) {
        return result;
      }
      switch (((int) (wParam))) {
        case ' ':
        case SWT.CR:
          Event event = new Event();
          event.text = ids[focusIndex];
          sendEvent(Selection, event);
          break;
        case SWT.TAB:
          boolean next = OS.GetKeyState(VK_SHIFT) >= 0;
          if (next) {
            if (focusIndex < (offsets.length - 1)) {
              focusIndex++;
              redraw();
            }
          } else if (focusIndex > 0) {
            focusIndex--;
            redraw();
          }
          break;
      }
    } else {
      switch (((int) (wParam))) {
        case ' ':
        case SWT.CR:
        case SWT.TAB:
          int code = callWindowProc(handle, WM_KEYDOWN, wParam, lParam);
          return new LRESULT(code);
      }
    }
    return result;
  }
}
