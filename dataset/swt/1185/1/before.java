class Cursor {
  public Cursor(Device device, int style) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    int lpCursorName = 0;
    switch (style) {
      case SWT.CURSOR_HAND:
        lpCursorName = OS.IDC_HAND;
        break;
      case SWT.CURSOR_ARROW:
        lpCursorName = OS.IDC_ARROW;
        break;
      case SWT.CURSOR_WAIT:
        lpCursorName = OS.IDC_WAIT;
        break;
      case SWT.CURSOR_CROSS:
        lpCursorName = OS.IDC_CROSS;
        break;
      case SWT.CURSOR_APPSTARTING:
        lpCursorName = OS.IDC_APPSTARTING;
        break;
      case SWT.CURSOR_HELP:
        lpCursorName = OS.IDC_HELP;
        break;
      case SWT.CURSOR_SIZEALL:
        lpCursorName = OS.IDC_SIZEALL;
        break;
      case SWT.CURSOR_SIZENESW:
        lpCursorName = OS.IDC_SIZENESW;
        break;
      case SWT.CURSOR_SIZENS:
        lpCursorName = OS.IDC_SIZENS;
        break;
      case SWT.CURSOR_SIZENWSE:
        lpCursorName = OS.IDC_SIZENWSE;
        break;
      case SWT.CURSOR_SIZEWE:
        lpCursorName = OS.IDC_SIZEWE;
        break;
      case SWT.CURSOR_SIZEN:
        lpCursorName = OS.IDC_SIZENS;
        break;
      case SWT.CURSOR_SIZES:
        lpCursorName = OS.IDC_SIZENS;
        break;
      case SWT.CURSOR_SIZEE:
        lpCursorName = OS.IDC_SIZEWE;
        break;
      case SWT.CURSOR_SIZEW:
        lpCursorName = OS.IDC_SIZEWE;
        break;
      case SWT.CURSOR_SIZENE:
        lpCursorName = OS.IDC_SIZENESW;
        break;
      case SWT.CURSOR_SIZESE:
        lpCursorName = OS.IDC_SIZENWSE;
        break;
      case SWT.CURSOR_SIZESW:
        lpCursorName = OS.IDC_SIZENESW;
        break;
      case SWT.CURSOR_SIZENW:
        lpCursorName = OS.IDC_SIZENWSE;
        break;
      case SWT.CURSOR_UPARROW:
        lpCursorName = OS.IDC_UPARROW;
        break;
      case SWT.CURSOR_IBEAM:
        lpCursorName = OS.IDC_IBEAM;
        break;
      case SWT.CURSOR_NO:
        lpCursorName = OS.IDC_NO;
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    handle = OS.LoadCursor(0, lpCursorName);
    if ((handle == 0) && (style == SWT.CURSOR_HAND)) {
      int width = OS.GetSystemMetrics(SM_CXCURSOR);
      int height = OS.GetSystemMetrics(SM_CYCURSOR);
      if ((width == 32) && (height == 32)) {
        int hInst = OS.GetModuleHandle(null);
        handle = OS.CreateCursor(hInst, 5, 0, 32, 32, HAND_SOURCE, HAND_MASK);
      }
    }
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
