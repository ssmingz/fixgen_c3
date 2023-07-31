class PlaceHold {
  public int internal_new_GC(GCData data) {
    checkWidget();
    if (!OS.XtIsRealized(handle)) {
      Shell shell = getShell();
      shell.realizeWidget();
    }
    int xDisplay = OS.XtDisplay(handle);
    if (xDisplay == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int xWindow = OS.XtWindow(handle);
    if (xWindow == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int xGC = OS.XCreateGC(xDisplay, xWindow, 0, null);
    if (xGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.XSetGraphicsExposures(xDisplay, xGC, false);
    int[] argList = new int[] {OS.XmNforeground, 0, OS.XmNbackground, 0, OS.XmNcolormap, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= style & (mask | SWT.MIRRORED);
      }
      data.device = display;
      data.display = xDisplay;
      data.drawable = xWindow;
      data.foreground = argList[1];
      data.background = argList[3];
      data.font = font;
      data.colormap = argList[5];
    }
    return xGC;
  }
}
