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
      case SWT.CURSOR_ARROW:
        shape = OS.XC_left_ptr;
        break;
      case SWT.CURSOR_WAIT:
        shape = OS.XC_watch;
        break;
      case SWT.CURSOR_HAND:
        shape = OS.XC_hand2;
        break;
      case SWT.CURSOR_CROSS:
        shape = OS.XC_cross;
        break;
      case SWT.CURSOR_APPSTARTING:
        if (!OS.IsLinux) {
          shape = OS.XC_left_ptr;
        }
        break;
      case SWT.CURSOR_HELP:
        shape = OS.XC_question_arrow;
        break;
      case SWT.CURSOR_SIZEALL:
        shape = OS.XC_fleur;
        break;
      case SWT.CURSOR_SIZENESW:
        shape = OS.XC_sizing;
        break;
      case SWT.CURSOR_SIZENS:
        shape = OS.XC_double_arrow;
        break;
      case SWT.CURSOR_SIZENWSE:
        shape = OS.XC_sizing;
        break;
      case SWT.CURSOR_SIZEWE:
        shape = OS.XC_sb_h_double_arrow;
        break;
      case SWT.CURSOR_SIZEN:
        shape = OS.XC_top_side;
        break;
      case SWT.CURSOR_SIZES:
        shape = OS.XC_bottom_side;
        break;
      case SWT.CURSOR_SIZEE:
        shape = OS.XC_right_side;
        break;
      case SWT.CURSOR_SIZEW:
        shape = OS.XC_left_side;
        break;
      case SWT.CURSOR_SIZENE:
        shape = OS.XC_top_right_corner;
        break;
      case SWT.CURSOR_SIZESE:
        shape = OS.XC_bottom_right_corner;
        break;
      case SWT.CURSOR_SIZESW:
        shape = OS.XC_bottom_left_corner;
        break;
      case SWT.CURSOR_SIZENW:
        shape = OS.XC_top_left_corner;
        break;
      case SWT.CURSOR_UPARROW:
        shape = OS.XC_sb_up_arrow;
        break;
      case SWT.CURSOR_IBEAM:
        shape = OS.XC_xterm;
        break;
      case SWT.CURSOR_NO:
        shape = OS.XC_X_cursor;
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((shape == 0) && (style == SWT.CURSOR_APPSTARTING)) {
      handle = createCursor(APPSTARTING_SRC, APPSTARTING_MASK, 32, 32, 2, 2, false);
    } else {
      handle = OS.XCreateFontCursor(device.xDisplay, shape);
    }
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
