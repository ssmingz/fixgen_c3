class PlaceHold {
  boolean translateAccelerator(int key, int keysym, XKeyEvent xEvent) {
    if (OS.IsSunOS) {
      if ((menuBar != null) && menuBar.getEnabled()) {
        switch (keysym) {
          case OS.XK_Control_L:
          case OS.XK_Control_R:
          case OS.XK_Alt_L:
          case OS.XK_Alt_R:
          case OS.XK_Shift_L:
          case OS.XK_Shift_R:
            return false;
        }
        if (keysym != 0) {
          switch (keysym) {
            case 0x1005ff10:
              keysym = OS.XK_F11;
              key = 0;
              break;
            case 0x1005ff11:
              keysym = OS.XK_F12;
              key = 0;
              break;
          }
          keysym &= 0xffff;
        }
        switch (keysym) {
          case OS.XK_ISO_Left_Tab:
            key = '\t';
            break;
          case OS.XK_space:
            key = ' ';
            break;
        }
        int accelerator = Display.translateKey(keysym);
        if (accelerator == 0) {
          accelerator = key;
        }
        if (accelerator == 0) {
          return false;
        }
        if ((xEvent.state & OS.Mod1Mask) != 0) {
          accelerator |= SWT.ALT;
        }
        if ((xEvent.state & OS.ShiftMask) != 0) {
          accelerator |= SWT.SHIFT;
        }
        if ((xEvent.state & OS.ControlMask) != 0) {
          accelerator |= SWT.CONTROL;
        }
        return menuBar.translateAccelerator(accelerator);
      }
    }
    return false;
  }
}
