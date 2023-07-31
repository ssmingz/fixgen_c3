class PlaceHold {
  void createHandle() {
    state |= (CANVAS | GRAB) | HIDDEN;
    int attributes = OS.kWindowStandardHandlerAttribute;
    attributes |= OS.kWindowCompositingAttribute;
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
        if ((style & SWT.TITLE) == 0) {
          attributes |= OS.kWindowLiveResizeAttribute;
        }
        if (!OS.__BIG_ENDIAN__()) {
          attributes |= OS.kWindowLiveResizeAttribute;
        }
      }
    }
    int windowClass = OS.kDocumentWindowClass;
    if ((style & (SWT.CLOSE | SWT.TITLE)) == 0) {
      windowClass = OS.kSheetWindowClass;
    }
    if ((style & ((SWT.APPLICATION_MODAL | SWT.PRIMARY_MODAL) | SWT.SYSTEM_MODAL)) != 0) {
      if ((style & ((SWT.CLOSE | SWT.MAX) | SWT.MIN)) == 0) {
        windowClass =
            ((style & SWT.TITLE) != 0) ? OS.kMovableModalWindowClass : OS.kModalWindowClass;
      }
    }
    if (shellHandle == 0) {
      Monitor monitor = getMonitor();
      Rectangle rect = monitor.getClientArea();
      int width = (rect.width * 5) / 8;
      int height = (rect.height * 5) / 8;
      Rect bounds = new Rect();
      OS.SetRect(bounds, ((short) (0)), ((short) (0)), ((short) (width)), ((short) (height)));
      int[] outWindow = new int[1];
      attributes &= OS.GetAvailableWindowAttributes(windowClass);
      OS.CreateNewWindow(windowClass, attributes, bounds, outWindow);
      if (outWindow[0] == 0) {
        error(ERROR_NO_HANDLES);
      }
      shellHandle = outWindow[0];
      OS.RepositionWindow(shellHandle, 0, kWindowCascadeOnMainScreen);
      int[] theRoot = new int[1];
      OS.HIViewFindByID(OS.HIViewGetRoot(shellHandle), OS.kHIViewWindowContentID(), theRoot);
      if (theRoot[0] == 0) {
        OS.GetRootControl(shellHandle, theRoot);
      }
      if (theRoot[0] == 0) {
        error(ERROR_NO_HANDLES);
      }
      if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) != 0) {
        createScrolledHandle(theRoot[0]);
      } else {
        createHandle(theRoot[0]);
      }
      OS.SetControlVisibility(topHandle(), false, false);
    } else {
      int[] theRoot = new int[1];
      OS.HIViewFindByID(shellHandle, OS.kHIViewWindowContentID(), theRoot);
      if (theRoot[0] == 0) {
        OS.GetRootControl(shellHandle, theRoot);
      }
      handle = OS.HIViewGetFirstSubview(theRoot[0]);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      if (OS.IsWindowVisible(shellHandle)) {
        state &= ~HIDDEN;
      }
    }
    int[] outGroup = new int[1];
    OS.CreateWindowGroup(kWindowGroupAttrHideOnCollapse, outGroup);
    if (outGroup[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    windowGroup = outGroup[0];
    int parentGroup;
    if ((style & SWT.ON_TOP) != 0) {
      parentGroup = OS.GetWindowGroupOfClass(kFloatingWindowClass);
    } else if (parent != null) {
      parentGroup = parent.getShell().windowGroup;
    } else {
      parentGroup = OS.GetWindowGroupOfClass(windowClass);
    }
    OS.SetWindowGroup(shellHandle, parentGroup);
    OS.SetWindowGroupParent(windowGroup, parentGroup);
    OS.SetWindowGroupOwner(windowGroup, shellHandle);
    CGPoint inMinLimits = new CGPoint();
    CGPoint inMaxLimits = new CGPoint();
    OS.GetWindowResizeLimits(shellHandle, inMinLimits, inMaxLimits);
    if (DEFAULT_CLIENT_WIDTH == (-1)) {
      DEFAULT_CLIENT_WIDTH = ((int) (inMinLimits.x));
    }
    if (DEFAULT_CLIENT_HEIGHT == (-1)) {
      DEFAULT_CLIENT_HEIGHT = 0;
    }
    inMinLimits.y = ((int) (0));
    int trim = ((SWT.TITLE | SWT.CLOSE) | SWT.MIN) | SWT.MAX;
    if (((style & SWT.NO_TRIM) != 0) || ((style & trim) == 0)) {
      inMinLimits.x = ((int) (0));
    }
    OS.SetWindowResizeLimits(shellHandle, inMinLimits, inMaxLimits);
    int[] docID = new int[1];
    OS.NewTSMDocument(((short) (1)), new int[] {OS.kUnicodeDocument}, docID, 0);
    if (docID[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    imHandle = docID[0];
  }
}
