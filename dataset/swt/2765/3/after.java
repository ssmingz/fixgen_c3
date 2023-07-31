class PlaceHold {
  public void setProgressState(int progressState) {
    checkWidget();
    if (shell == null) {
      return;
    }
    if (this.progressState == progressState) {
      return;
    }
    this.progressState = progressState;
    int tbpFlags = OS.TBPF_NOPROGRESS;
    switch (progressState) {
      case SWT.NORMAL:
        tbpFlags = OS.TBPF_NORMAL;
        break;
      case SWT.ERROR:
        tbpFlags = OS.TBPF_ERROR;
        break;
      case SWT.PAUSED:
        tbpFlags = OS.TBPF_PAUSED;
        break;
      case SWT.INDETERMINATE:
        tbpFlags = OS.TBPF_INDETERMINATE;
        break;
    }
    int mTaskbarList3 = parent.mTaskbarList3;
    int hwnd = shell.handle;
    OS.VtblCall(9, mTaskbarList3, hwnd, ((long) (progress)), ((long) (PROGRESS_MAX)));
    OS.VtblCall(10, mTaskbarList3, hwnd, tbpFlags);
  }
}
