class PlaceHold {
  protected void init() {
    if (debug) {
      OS.XSynchronize(xDisplay, true);
    }
    Class clazz = getClass();
    synchronized (clazz) {
      if (XErrorCallback == null) {
        XErrorCallback = new Callback(clazz, "XErrorProc", 2);
        XNullErrorProc = XErrorCallback.getAddress();
        if (XNullErrorProc == 0) {
          SWT.error(ERROR_NO_MORE_CALLBACKS);
        }
        XErrorProc = OS.XSetErrorHandler(XNullErrorProc);
      }
      if (XIOErrorCallback == null) {
        XIOErrorCallback = new Callback(clazz, "XIOErrorProc", 1);
        XNullIOErrorProc = XIOErrorCallback.getAddress();
        if (XNullIOErrorProc == 0) {
          SWT.error(ERROR_NO_MORE_CALLBACKS);
        }
        XIOErrorProc = OS.XSetIOErrorHandler(XNullIOErrorProc);
      }
    }
    xtWarningCallback = new Callback(this, "xtWarningProc", 1);
    xtNullWarningProc = xtWarningCallback.getAddress();
    if (xtNullWarningProc == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    xtErrorCallback = new Callback(this, "xtErrorProc", 1);
    xtNullErrorProc = xtErrorCallback.getAddress();
    if (xtNullErrorProc == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    int xtContext = OS.XtDisplayToApplicationContext(xDisplay);
    xtWarningProc = OS.XtAppSetWarningHandler(xtContext, xtNullWarningProc);
    xtErrorProc = OS.XtAppSetErrorHandler(xtContext, xtNullErrorProc);
    int xScreenPtr = OS.XDefaultScreenOfDisplay(xDisplay);
    int defaultDepth = OS.XDefaultDepthOfScreen(xScreenPtr);
    if (defaultDepth <= 8) {
      int numColors = 1 << defaultDepth;
      colorRefCount = new int[numColors];
      xcolors = new XColor[numColors];
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
    int widgetClass = OS.topLevelShellWidgetClass();
    shellHandle = OS.XtAppCreateShell(null, null, widgetClass, xDisplay, null, 0);
    if (shellHandle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    byte[] tabBuffer = new byte[] {((byte) ('\t')), 0};
    tabPointer = OS.XtMalloc(tabBuffer.length);
    OS.memmove(tabPointer, tabBuffer, tabBuffer.length);
    int tabString = OS.XmStringComponentCreate(XmSTRING_COMPONENT_TAB, 0, null);
    int[] argList = new int[] {OS.XmNpattern, tabPointer, OS.XmNsubstitute, tabString};
    tabMapping = OS.XmParseMappingCreate(argList, argList.length / 2);
    OS.XmStringFree(tabString);
    byte[] crBuffer = new byte[] {((byte) ('\n')), 0};
    crPointer = OS.XtMalloc(crBuffer.length);
    OS.memmove(crPointer, crBuffer, crBuffer.length);
    int crString = OS.XmStringComponentCreate(XmSTRING_COMPONENT_SEPARATOR, 0, null);
    argList = new int[] {OS.XmNpattern, crPointer, OS.XmNsubstitute, crString};
    crMapping = OS.XmParseMappingCreate(argList, argList.length / 2);
    OS.XmStringFree(crString);
  }
}
