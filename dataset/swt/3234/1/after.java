class PlaceHold {
  static boolean cde_init(Display display) {
    try {
      Library.loadLibrary("swt-cde");
    } catch (Throwable e) {
      return false;
    }
    byte[] appName = Converter.wcsToMbcs(null, "SWT", true);
    int xtContext = OS.XtDisplayToApplicationContext(display.xDisplay);
    int widgetClass = OS.TopLevelShellWidgetClass();
    int shell = OS.XtAppCreateShell(appName, appName, widgetClass, display.xDisplay, null, 0);
    boolean initOK = CDE.DtAppInitialize(xtContext, display.xDisplay, shell, appName, appName);
    if (!initOK) {
      OS.XtDestroyWidget(shell);
    } else {
      CDE.DtDbLoad();
      display.setData(cdeShell, new Integer(shell));
      display.disposeExec(
          new Runnable() {
            public void run() {
              Integer shell = ((Integer) (Display.getCurrent().getData(cdeShell)));
              if (shell != null) {
                OS.XtDestroyWidget(shell.intValue());
              }
            }
          });
    }
    return initOK;
  }
}
