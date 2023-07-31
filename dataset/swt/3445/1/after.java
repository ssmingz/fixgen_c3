class Cursor {
  public Cursor(Device device, int style) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    int shape = 0;
    switch (style) {
      case SWT.CURSOR_APPSTARTING:
        break;
      case SWT.CURSOR_ARROW:
        shape = OS.GDK_LEFT_PTR;
        break;
      case SWT.CURSOR_WAIT:
        shape = OS.GDK_WATCH;
        break;
      case SWT.CURSOR_CROSS:
        shape = OS.GDK_CROSS;
        break;
      case SWT.CURSOR_HAND:
        shape = OS.GDK_HAND1;
        break;
      case SWT.CURSOR_HELP:
        shape = OS.GDK_QUESTION_ARROW;
        break;
      case SWT.CURSOR_SIZEALL:
        shape = OS.GDK_FLEUR;
        break;
      case SWT.CURSOR_SIZENESW:
        shape = OS.GDK_SIZING;
        break;
      case SWT.CURSOR_SIZENS:
        shape = OS.GDK_DOUBLE_ARROW;
        break;
      case SWT.CURSOR_SIZENWSE:
        shape = OS.GDK_SIZING;
        break;
      case SWT.CURSOR_SIZEWE:
        shape = OS.GDK_SB_H_DOUBLE_ARROW;
        break;
      case SWT.CURSOR_SIZEN:
        shape = OS.GDK_TOP_SIDE;
        break;
      case SWT.CURSOR_SIZES:
        shape = OS.GDK_BOTTOM_SIDE;
        break;
      case SWT.CURSOR_SIZEE:
        shape = OS.GDK_RIGHT_SIDE;
        break;
      case SWT.CURSOR_SIZEW:
        shape = OS.GDK_LEFT_SIDE;
        break;
      case SWT.CURSOR_SIZENE:
        shape = OS.GDK_TOP_RIGHT_CORNER;
        break;
      case SWT.CURSOR_SIZESE:
        shape = OS.GDK_BOTTOM_RIGHT_CORNER;
        break;
      case SWT.CURSOR_SIZESW:
        shape = OS.GDK_BOTTOM_LEFT_CORNER;
        break;
      case SWT.CURSOR_SIZENW:
        shape = OS.GDK_TOP_LEFT_CORNER;
        break;
      case SWT.CURSOR_UPARROW:
        shape = OS.GDK_SB_UP_ARROW;
        break;
      case SWT.CURSOR_IBEAM:
        shape = OS.GDK_XTERM;
        break;
      case SWT.CURSOR_NO:
        shape = OS.GDK_X_CURSOR;
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((shape == 0) && (style == SWT.CURSOR_APPSTARTING)) {
      handle = createCursor(APPSTARTING_SRC, APPSTARTING_MASK, 32, 32, 2, 2, true);
    } else {
      handle = OS.gdk_cursor_new(shape);
    }
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
