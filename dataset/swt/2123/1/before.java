class PlaceHold {
  void setWindowModal(Dialog dialog, boolean modal, boolean destroy) {
    if (dialog == null) {
      return;
    }
    if ((style & ((SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL) | SWT.PRIMARY_MODAL)) == 0) {
      return;
    }
    if (isDisposed()) {
      return;
    }
    int dialogHandle = 0;
    if (dialog instanceof FileDialog) {
      dialogHandle = ((FileDialog) (dialog)).dialog;
    }
    if (dialog instanceof DirectoryDialog) {
      dialogHandle = ((DirectoryDialog) (dialog)).dialog;
    }
    if (dialogHandle == 0) {
      return;
    }
    if (modal) {
      OS.SetWindowModality(OS.NavDialogGetWindow(dialogHandle), kWindowModalityNone, 0);
      int windowClass =
          ((style & SWT.TITLE) != 0) ? OS.kMovableModalWindowClass : OS.kModalWindowClass;
      OS.SetWindowGroup(shellHandle, OS.GetWindowGroupOfClass(windowClass));
      OS.SelectWindow(shellHandle);
    } else {
      if (!destroy) {
        int parentGroup;
        if ((style & SWT.ON_TOP) != 0) {
          parentGroup = OS.GetWindowGroupOfClass(kFloatingWindowClass);
        } else if (parent != null) {
          parentGroup = parent.getShell().windowGroup;
        } else {
          parentGroup = OS.GetWindowGroupOfClass(kDocumentWindowClass);
        }
        OS.SetWindowGroup(shellHandle, parentGroup);
      }
      OS.SetWindowModality(OS.NavDialogGetWindow(dialogHandle), kWindowModalityAppModal, 0);
    }
  }
}
