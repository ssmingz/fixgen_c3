class PlaceHold {
  public boolean post(Event event) {
    Lock lock = OS.lock;
    lock.lock();
    try {
      synchronized (Device.class) {
        if (isDisposed()) {
          error(ERROR_DEVICE_DISPOSED);
        }
        if (event == null) {
          error(ERROR_NULL_ARGUMENT);
        }
        if (!OS.IS_X11) {
          return false;
        }
        long xDisplay = OS.gdk_x11_display_get_xdisplay(OS.gdk_display_get_default());
        int type = event.type;
        switch (type) {
          case SWT.KeyDown:
          case SWT.KeyUp:
            {
              int keyCode = 0;
              long keysym = untranslateKey(event.keyCode);
              if (keysym != 0) {
                keyCode = OS.XKeysymToKeycode(xDisplay, keysym);
              }
              if (keyCode == 0) {
                char key = event.character;
                switch (key) {
                  case SWT.BS:
                    keysym = OS.GDK_BackSpace;
                    break;
                  case SWT.CR:
                    keysym = OS.GDK_Return;
                    break;
                  case SWT.DEL:
                    keysym = OS.GDK_Delete;
                    break;
                  case SWT.ESC:
                    keysym = OS.GDK_Escape;
                    break;
                  case SWT.TAB:
                    keysym = OS.GDK_Tab;
                    break;
                  case SWT.LF:
                    keysym = OS.GDK_Linefeed;
                    break;
                  default:
                    keysym = key;
                }
                keyCode = OS.XKeysymToKeycode(xDisplay, keysym);
                if (keyCode == 0) {
                  return false;
                }
              }
              OS.XTestFakeKeyEvent(xDisplay, keyCode, type == SWT.KeyDown, 0);
              return true;
            }
          case SWT.MouseDown:
          case SWT.MouseMove:
          case SWT.MouseUp:
            {
              if (type == SWT.MouseMove) {
                OS.XTestFakeMotionEvent(xDisplay, -1, event.x, event.y, 0);
              } else {
                int button = event.button;
                switch (button) {
                  case 1:
                  case 2:
                  case 3:
                    break;
                  case 4:
                    button = 6;
                    break;
                  case 5:
                    button = 7;
                    break;
                  default:
                    return false;
                }
                OS.XTestFakeButtonEvent(xDisplay, button, type == SWT.MouseDown, 0);
              }
              return true;
            }
        }
        return false;
      }
    } finally {
      lock.unlock();
    }
  }
}
