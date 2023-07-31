class PlaceHold {
  protected void init() {
    if (OS.GDK_WINDOWING_X11()) {
      xDisplay = OS.GDK_DISPLAY();
    }
    if (debug) {
      if (OS.GDK_WINDOWING_X11()) {
        Class clazz = getClass();
        synchronized (clazz) {
          int index = 0;
          while (index < Device.Devices.length) {
            if (Devices[index] != null) {
              break;
            }
            index++;
          }
          if (index == Device.Devices.length) {
            XErrorCallback = new Callback(clazz, "XErrorProc", 2);
            XNullErrorProc = XErrorCallback.getAddress();
            if (XNullErrorProc == 0) {
              SWT.error(ERROR_NO_MORE_CALLBACKS);
            }
            XIOErrorCallback = new Callback(clazz, "XIOErrorProc", 1);
            XNullIOErrorProc = XIOErrorCallback.getAddress();
            if (XNullIOErrorProc == 0) {
              SWT.error(ERROR_NO_MORE_CALLBACKS);
            }
            XErrorProc = OS.XSetErrorHandler(XNullErrorProc);
            XIOErrorProc = OS.XSetIOErrorHandler(XNullIOErrorProc);
          }
        }
        OS.XSynchronize(xDisplay, true);
      }
    }
    logCallback = new Callback(this, "logProc", 4);
    logProc = logCallback.getAddress();
    if (logProc == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    if (debug) {
      int flags = (OS.G_LOG_LEVEL_MASK | OS.G_LOG_FLAG_FATAL) | OS.G_LOG_FLAG_RECURSION;
      for (int i = 0; i < log_domains.length; i++) {
        byte[] log_domain = Converter.wcsToMbcs(null, log_domains[i], true);
        handler_ids[i] = OS.g_log_set_handler(log_domain, flags, logProc, 0);
      }
    }
    COLOR_BLACK = new Color(this, 0, 0, 0);
    COLOR_DARK_RED = new Color(this, 0x80, 0, 0);
    COLOR_DARK_GREEN = new Color(this, 0, 0x80, 0);
    COLOR_DARK_YELLOW = new Color(this, 0x80, 0x80, 0);
    COLOR_DARK_BLUE = new Color(this, 0, 0, 0x80);
    COLOR_DARK_MAGENTA = new Color(this, 0x80, 0, 0x80);
    COLOR_DARK_CYAN = new Color(this, 0, 0x80, 0x80);
    COLOR_GRAY = new Color(this, 0xc0, 0xc0, 0xc0);
    COLOR_DARK_GRAY = new Color(this, 0x80, 0x80, 0x80);
    COLOR_RED = new Color(this, 0xff, 0, 0);
    COLOR_GREEN = new Color(this, 0, 0xff, 0);
    COLOR_YELLOW = new Color(this, 0xff, 0xff, 0);
    COLOR_BLUE = new Color(this, 0, 0, 0xff);
    COLOR_MAGENTA = new Color(this, 0xff, 0, 0xff);
    COLOR_CYAN = new Color(this, 0, 0xff, 0xff);
    COLOR_WHITE = new Color(this, 0xff, 0xff, 0xff);
    emptyTab = OS.pango_tab_array_new(1, false);
    if (emptyTab == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.pango_tab_array_set_tab(emptyTab, 0, PANGO_TAB_LEFT, 1);
    shellHandle = OS.gtk_window_new(GTK_WINDOW_TOPLEVEL);
    if (shellHandle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.gtk_widget_realize(shellHandle);
  }
}
