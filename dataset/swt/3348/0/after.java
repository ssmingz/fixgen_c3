class PlaceHold {
  void propagateHandle(boolean enabled, int widgetHandle) {
    int xDisplay = OS.XtDisplay(widgetHandle);
    if (xDisplay == 0) {
      return;
    }
    int xWindow = OS.XtWindow(widgetHandle);
    if (xWindow == 0) {
      return;
    }
    int event_mask = OS.XtBuildEventMask(widgetHandle);
    int do_not_propagate_mask =
        (((OS.KeyPressMask | OS.KeyReleaseMask) | OS.ButtonPressMask) | OS.ButtonReleaseMask)
            | OS.PointerMotionMask;
    if (!enabled) {
      event_mask &= ~((do_not_propagate_mask | OS.EnterWindowMask) | OS.LeaveWindowMask);
      do_not_propagate_mask = 0;
    }
    XSetWindowAttributes attributes = new XSetWindowAttributes();
    attributes.event_mask = event_mask;
    attributes.do_not_propagate_mask = do_not_propagate_mask;
    OS.XChangeWindowAttributes(xDisplay, xWindow, OS.CWDontPropagate | OS.CWEventMask, attributes);
    int[] argList = new int[] {OS.XmNtraversalOn, enabled ? 1 : 0};
    OS.XtSetValues(widgetHandle, argList, argList.length / 2);
    if (argList[1] != 0) {
      Display display = getDisplay();
      OS.XtOverrideTranslations(handle, display.tabTranslations);
      OS.XtOverrideTranslations(handle, display.arrowTranslations);
    }
  }
}
