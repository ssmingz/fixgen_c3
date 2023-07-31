class PlaceHold {
  public int open() {
    int buttonBits = 0;
    if ((style & SWT.OK) == SWT.OK) {
      buttonBits = OS.MB_OK;
    }
    if ((style & (SWT.OK | SWT.CANCEL)) == (SWT.OK | SWT.CANCEL)) {
      buttonBits = OS.MB_OKCANCEL;
    }
    if ((style & (SWT.YES | SWT.NO)) == (SWT.YES | SWT.NO)) {
      buttonBits = OS.MB_YESNO;
    }
    if ((style & ((SWT.YES | SWT.NO) | SWT.CANCEL)) == ((SWT.YES | SWT.NO) | SWT.CANCEL)) {
      buttonBits = OS.MB_YESNOCANCEL;
    }
    if ((style & (SWT.RETRY | SWT.CANCEL)) == (SWT.RETRY | SWT.CANCEL)) {
      buttonBits = OS.MB_RETRYCANCEL;
    }
    if ((style & ((SWT.ABORT | SWT.RETRY) | SWT.IGNORE))
        == ((SWT.ABORT | SWT.RETRY) | SWT.IGNORE)) {
      buttonBits = OS.MB_ABORTRETRYIGNORE;
    }
    if (buttonBits == 0) {
      buttonBits = OS.MB_OK;
    }
    int iconBits = 0;
    if ((style & SWT.ICON_ERROR) != 0) {
      iconBits = OS.MB_ICONERROR;
    }
    if ((style & SWT.ICON_INFORMATION) != 0) {
      iconBits = OS.MB_ICONINFORMATION;
    }
    if ((style & SWT.ICON_QUESTION) != 0) {
      iconBits = OS.MB_ICONQUESTION;
    }
    if ((style & SWT.ICON_WARNING) != 0) {
      iconBits = OS.MB_ICONWARNING;
    }
    if ((style & SWT.ICON_WORKING) != 0) {
      iconBits = OS.MB_ICONINFORMATION;
    }
    int modalBits = 0;
    if (OS.IsWinCE) {
      if ((style & ((SWT.PRIMARY_MODAL | SWT.APPLICATION_MODAL) | SWT.SYSTEM_MODAL)) != 0) {
        modalBits = OS.MB_APPLMODAL;
      }
    } else {
      if ((style & SWT.PRIMARY_MODAL) != 0) {
        modalBits = OS.MB_APPLMODAL;
      }
      if ((style & SWT.APPLICATION_MODAL) != 0) {
        modalBits = OS.MB_TASKMODAL;
      }
      if ((style & SWT.SYSTEM_MODAL) != 0) {
        modalBits = OS.MB_SYSTEMMODAL;
      }
    }
    int bits = (buttonBits | iconBits) | modalBits;
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      bits |= OS.MB_RTLREADING | OS.MB_RIGHT;
    }
    if ((style & (SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT)) == 0) {
      if ((parent != null) && ((parent.style & SWT.MIRRORED) != 0)) {
        bits |= OS.MB_RTLREADING | OS.MB_RIGHT;
      }
    }
    if ((bits & OS.MB_SYSTEMMODAL) != 0) {
      bits |= OS.MB_TASKMODAL;
      bits &= ~OS.MB_SYSTEMMODAL;
      bits |= OS.MB_TOPMOST;
    }
    int hwndOwner = (parent != null) ? parent.handle : 0;
    Dialog oldModal = null;
    Display display = null;
    if ((bits & OS.MB_TASKMODAL) != 0) {
      display = parent.getDisplay();
      oldModal = display.getModalDialog();
      display.setModalDialog(this);
    }
    TCHAR buffer1 = new TCHAR(0, message, true);
    TCHAR buffer2 = new TCHAR(0, title, true);
    int code = OS.MessageBox(hwndOwner, buffer1, buffer2, bits);
    if ((bits & OS.MB_TASKMODAL) != 0) {
      display.setModalDialog(oldModal);
    }
    if (code != 0) {
      int type = bits & 0xf;
      if (type == OS.MB_OK) {
        return SWT.OK;
      }
      if (type == OS.MB_OKCANCEL) {
        return code == OS.IDOK ? SWT.OK : SWT.CANCEL;
      }
      if (type == OS.MB_YESNO) {
        return code == OS.IDYES ? SWT.YES : SWT.NO;
      }
      if (type == OS.MB_YESNOCANCEL) {
        if (code == OS.IDYES) {
          return SWT.YES;
        }
        if (code == OS.IDNO) {
          return SWT.NO;
        }
        return SWT.CANCEL;
      }
      if (type == OS.MB_RETRYCANCEL) {
        return code == OS.IDRETRY ? SWT.RETRY : SWT.CANCEL;
      }
      if (type == OS.MB_ABORTRETRYIGNORE) {
        if (code == OS.IDRETRY) {
          return SWT.RETRY;
        }
        if (code == OS.IDABORT) {
          return SWT.ABORT;
        }
        return SWT.IGNORE;
      }
    }
    return SWT.CANCEL;
  }
}
