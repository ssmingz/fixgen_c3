class PlaceHold {
  void createHandle(int index) {
    state |= CANVAS;
    int decorations = 0;
    if ((style & SWT.NO_TRIM) == 0) {
      if ((style & SWT.MIN) != 0) {
        decorations |= OS.MWM_DECOR_MINIMIZE;
      }
      if ((style & SWT.MAX) != 0) {
        decorations |= OS.MWM_DECOR_MAXIMIZE;
      }
      if ((style & SWT.RESIZE) != 0) {
        decorations |= OS.MWM_DECOR_RESIZEH;
      }
      if ((style & SWT.BORDER) != 0) {
        decorations |= OS.MWM_DECOR_BORDER;
      }
      if ((style & SWT.MENU) != 0) {
        decorations |= OS.MWM_DECOR_MENU;
      }
      if ((style & SWT.TITLE) != 0) {
        decorations |= OS.MWM_DECOR_TITLE;
      }
      if ((style & SWT.RESIZE) != 0) {
        decorations |= OS.MWM_DECOR_BORDER;
      }
    }
    int inputMode = OS.MWM_INPUT_MODELESS;
    if ((style & SWT.PRIMARY_MODAL) != 0) {
      inputMode = OS.MWM_INPUT_PRIMARY_APPLICATION_MODAL;
    }
    if ((style & SWT.APPLICATION_MODAL) != 0) {
      inputMode = OS.MWM_INPUT_FULL_APPLICATION_MODAL;
    }
    if ((style & SWT.SYSTEM_MODAL) != 0) {
      inputMode = OS.MWM_INPUT_SYSTEM_MODAL;
    }
    byte[] buffer = new byte[] {((byte) (' ')), 0, 0, 0};
    int ptr = OS.XtMalloc(buffer.length);
    OS.memmove(ptr, buffer, buffer.length);
    int[] argList1 =
        new int[] {
          OS.XmNmwmInputMode,
          inputMode,
          OS.XmNmwmDecorations,
          decorations,
          OS.XmNoverrideRedirect,
          (style & SWT.ON_TOP) != 0 ? 1 : 0,
          OS.XmNtitle,
          ptr
        };
    int orientations = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
    if (((style & (~orientations)) == SWT.NONE) || ((style & (SWT.NO_TRIM | SWT.ON_TOP)) != 0)) {
      reparented = true;
    }
    byte[] appClass = display.appClass;
    if (((parent == null) && ((style & SWT.ON_TOP) == 0))
        && (inputMode != OS.MWM_INPUT_FULL_APPLICATION_MODAL)) {
      int xDisplay = display.xDisplay;
      int widgetClass = OS.applicationShellWidgetClass();
      shellHandle =
          OS.XtAppCreateShell(
              appName, appClass, widgetClass, xDisplay, argList1, argList1.length / 2);
    } else {
      int widgetClass = OS.transientShellWidgetClass();
      int parentHandle = display.shellHandle;
      if (parent != null) {
        parentHandle = parent.handle;
      }
      shellHandle =
          OS.XtCreatePopupShell(appClass, widgetClass, parentHandle, argList1, argList1.length / 2);
    }
    OS.XtFree(ptr);
    if (shellHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (handle != 0) {
      OS.XtSetMappedWhenManaged(shellHandle, false);
      OS.XtRealizeWidget(shellHandle);
      OS.XtSetMappedWhenManaged(shellHandle, true);
      int xDisplay = display.xDisplay;
      int xWindow = OS.XtWindow(shellHandle);
      if (xWindow == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.XReparentWindow(xDisplay, xWindow, handle, 0, 0);
      handle = 0;
    }
    createHandle(index, shellHandle, true);
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.RESIZE)) == 0) {
      int[] argList2 = new int[] {OS.XmNborderWidth, 1};
      OS.XtSetValues(handle, argList2, argList2.length / 2);
    }
    if ((style & SWT.ON_TOP) == 0) {
      int[] argList3 = new int[] {OS.XmNtraversalOn, 0};
      int textHandle = OS.XmCreateTextField(handle, null, argList3, argList3.length / 2);
      if (textHandle == 0) {
        error(ERROR_NO_HANDLES);
      }
    }
  }
}
