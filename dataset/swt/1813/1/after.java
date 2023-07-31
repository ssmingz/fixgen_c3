class PlaceHold {
  public int open() {
    boolean destroyContext;
    Display appContext = Display.getCurrent();
    if (destroyContext = appContext == null) {
      appContext = new Display();
    }
    int parentHandle = appContext.shellHandle;
    if ((parent != null) && (parent.display == appContext)) {
      parentHandle = parent.shellHandle;
    }
    String string = title;
    if (string.length() == 0) {
      string = " ";
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int xmStringPtr =
        OS.XmStringParseText(buffer, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, null, 0, 0);
    int dialogStyle = OS.XmDIALOG_MODELESS;
    if ((style & SWT.PRIMARY_MODAL) != 0) {
      dialogStyle = OS.XmDIALOG_PRIMARY_APPLICATION_MODAL;
    }
    if ((style & SWT.APPLICATION_MODAL) != 0) {
      dialogStyle = OS.XmDIALOG_FULL_APPLICATION_MODAL;
    }
    if ((style & SWT.SYSTEM_MODAL) != 0) {
      dialogStyle = OS.XmDIALOG_SYSTEM_MODAL;
    }
    if ((parent != null) && (dialogStyle == OS.XmDIALOG_MODELESS)) {
      dialogStyle = OS.XmDIALOG_PRIMARY_APPLICATION_MODAL;
    }
    int[] argList =
        new int[] {
          OS.XmNnoResize,
          1,
          OS.XmNresizePolicy,
          OS.XmRESIZE_NONE,
          OS.XmNdialogStyle,
          dialogStyle,
          OS.XmNdialogTitle,
          xmStringPtr
        };
    int dialog = createHandle(parentHandle, argList);
    if (dialog == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.XmStringFree(xmStringPtr);
    setMessage(dialog);
    setButtons(dialog);
    Callback callback = new Callback(this, "activate", 3);
    int address = callback.getAddress();
    OS.XtAddCallback(dialog, XmNokCallback, address, XmDIALOG_OK_BUTTON);
    OS.XtAddCallback(dialog, XmNcancelCallback, address, XmDIALOG_CANCEL_BUTTON);
    OS.XtAddCallback(dialog, XmNhelpCallback, address, XmDIALOG_HELP_BUTTON);
    OS.XtManageChild(dialog);
    while (OS.XtIsRealized(dialog) && OS.XtIsManaged(dialog)) {
      if (!appContext.readAndDispatch()) {
        appContext.sleep();
      }
    }
    if (OS.XtIsRealized(dialog)) {
      OS.XtDestroyWidget(dialog);
    }
    if (destroyContext) {
      appContext.dispose();
    }
    callback.dispose();
    if ((style & ((SWT.YES | SWT.NO) | SWT.CANCEL)) == ((SWT.YES | SWT.NO) | SWT.CANCEL)) {
      if (button == OS.XmDIALOG_OK_BUTTON) {
        return SWT.YES;
      }
      if (button == OS.XmDIALOG_CANCEL_BUTTON) {
        return SWT.NO;
      }
      return SWT.CANCEL;
    }
    if ((style & (SWT.YES | SWT.NO)) == (SWT.YES | SWT.NO)) {
      return button == OS.XmDIALOG_OK_BUTTON ? SWT.YES : SWT.NO;
    }
    if ((style & (SWT.OK | SWT.CANCEL)) == (SWT.OK | SWT.CANCEL)) {
      return button == OS.XmDIALOG_OK_BUTTON ? SWT.OK : SWT.CANCEL;
    }
    if ((style & SWT.OK) == SWT.OK) {
      return SWT.OK;
    }
    if ((style & (SWT.RETRY | SWT.CANCEL)) == (SWT.RETRY | SWT.CANCEL)) {
      return button == OS.XmDIALOG_OK_BUTTON ? SWT.RETRY : SWT.CANCEL;
    }
    if ((style & ((SWT.ABORT | SWT.RETRY) | SWT.IGNORE))
        == ((SWT.ABORT | SWT.RETRY) | SWT.IGNORE)) {
      if (button == OS.XmDIALOG_OK_BUTTON) {
        return SWT.ABORT;
      }
      if (button == OS.XmDIALOG_CANCEL_BUTTON) {
        return SWT.RETRY;
      }
      return SWT.IGNORE;
    }
    return SWT.CANCEL;
  }
}
