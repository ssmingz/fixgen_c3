class PlaceHold {
  LRESULT WM_MOUSEACTIVATE(int wParam, int lParam) {
    LRESULT result = super.WM_MOUSEACTIVATE(wParam, lParam);
    if (result != null) {
      return result;
    }
    int hittest = ((short) (OS.LOWORD(lParam)));
    switch (hittest) {
      case OS.HTERROR:
      case OS.HTTRANSPARENT:
      case OS.HTNOWHERE:
        break;
      default:
        {
          Control control = display._getFocusControl();
          if (control != null) {
            Decorations decorations = control.menuShell();
            if ((decorations.getShell() == this) && (decorations != this)) {
              display.ignoreRestoreFocus = true;
              display.lastHittest = hittest;
              display.lastHittestControl = null;
              if ((hittest == OS.HTMENU) || (hittest == OS.HTSYSMENU)) {
                display.lastHittestControl = control;
                return null;
              }
              if (OS.IsWin95 && (hittest == OS.HTCAPTION)) {
                display.lastHittestControl = control;
              }
              return new LRESULT(OS.MA_NOACTIVATE);
            }
          }
        }
    }
    if (hittest == OS.HTMENU) {
      return null;
    }
    POINT pt = new POINT();
    if (!OS.GetCursorPos(pt)) {
      int pos = OS.GetMessagePos();
      OS.POINTSTOPOINT(pt, pos);
    }
    int hwnd = OS.WindowFromPoint(pt);
    if (hwnd == 0) {
      return null;
    }
    Control control = display.findControl(hwnd);
    if ((control != null) && ((control.state & CANVAS) != 0)) {
      if ((control.style & SWT.NO_FOCUS) != 0) {
        int bits = SWT.ON_TOP | SWT.NO_FOCUS;
        if ((style & bits) == bits) {
          if ((hittest == OS.HTBORDER) || (hittest == OS.HTCLIENT)) {
            return new LRESULT(OS.MA_NOACTIVATE);
          }
        }
      }
    }
    setActiveControl(control);
    return null;
  }
}
