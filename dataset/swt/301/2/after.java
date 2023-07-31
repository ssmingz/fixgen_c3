class PlaceHold {
  LRESULT WM_MOUSEACTIVATE(int wParam, int lParam) {
    LRESULT result = super.WM_MOUSEACTIVATE(wParam, lParam);
    if (result != null) {
      return result;
    }
    int hittest = ((short) (lParam & 0xffff));
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
      pt.x = ((short) (pos & 0xffff));
      pt.y = ((short) (pos >> 16));
    }
    int hwnd = OS.WindowFromPoint(pt);
    if (hwnd == 0) {
      return null;
    }
    Control control = display.findControl(hwnd);
    setActiveControl(control);
    return null;
  }
}
