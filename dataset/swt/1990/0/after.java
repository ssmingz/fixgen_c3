class PlaceHold {
  void createHandle() {
    state |= (CANVAS | GRAB) | HIDDEN;
    int attributes = OS.kWindowStandardHandlerAttribute;
    if ((style & SWT.NO_TRIM) == 0) {
      if ((style & SWT.CLOSE) != 0) {
        attributes |= OS.kWindowCloseBoxAttribute;
      }
      if ((style & SWT.MIN) != 0) {
        attributes |= OS.kWindowCollapseBoxAttribute;
      }
      if ((style & SWT.MAX) != 0) {
        attributes |= OS.kWindowFullZoomAttribute;
      }
      if ((style & SWT.RESIZE) != 0) {
        attributes |= OS.kWindowResizableAttribute;
      }
    }
    int windowClass = OS.kDocumentWindowClass;
    if ((style & (SWT.CLOSE | SWT.TITLE)) == 0) {
      windowClass = OS.kSheetWindowClass;
    }
    Rect rect = new Rect();
    OS.GetAvailableWindowPositioningBounds(OS.GetMainDevice(), rect);
    int width = ((rect.right - rect.left) * 5) / 8;
    int height = ((rect.bottom - rect.top) * 5) / 8;
    OS.SetRect(rect, ((short) (0)), ((short) (0)), ((short) (width)), ((short) (height)));
    int[] outWindow = new int[1];
    attributes &= OS.GetAvailableWindowAttributes(windowClass);
    OS.CreateNewWindow(windowClass, attributes, rect, outWindow);
    if (outWindow[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    shellHandle = outWindow[0];
    if ((style & SWT.ON_TOP) != 0) {
      OS.SetWindowActivationScope(shellHandle, kWindowActivationScopeNone);
    }
    OS.RepositionWindow(shellHandle, 0, kWindowCascadeOnMainScreen);
    int[] theRoot = new int[1];
    OS.CreateRootControl(shellHandle, theRoot);
    OS.GetRootControl(shellHandle, theRoot);
    if (theRoot[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) != 0) {
      createScrolledHandle(theRoot[0]);
    } else {
      createHandle(theRoot[0]);
    }
    OS.SetControlVisibility(topHandle(), false, false);
    int[] outGroup = new int[1];
    OS.CreateWindowGroup(kWindowGroupAttrHideOnCollapse, outGroup);
    if (outGroup[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    windowGroup = outGroup[0];
    if (parent != null) {
      Shell shell = parent.getShell();
      int parentGroup = shell.windowGroup;
      OS.SetWindowGroup(shellHandle, parentGroup);
      OS.SetWindowGroupParent(windowGroup, parentGroup);
    } else {
      int parentGroup = OS.GetWindowGroupOfClass(windowClass);
      OS.SetWindowGroupParent(windowGroup, parentGroup);
    }
    OS.SetWindowGroupOwner(windowGroup, shellHandle);
  }
}
