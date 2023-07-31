class PlaceHold {
  public void setProgress(int progress) {
    checkWidget();
    progress = Math.max(0, Math.min(progress, PROGRESS_MAX));
    if (this.progress == progress) {
      return;
    }
    this.progress = progress;
    if (progressState == SWT.INDETERMINATE) {
      return;
    }
    if (progressState == SWT.DEFAULT) {
      return;
    }
    int mTaskbarList3 = parent.mTaskbarList3;
    int hwnd = shell.handle;
    OS.VtblCall(9, mTaskbarList3, hwnd, ((long) (progress)), ((long) (PROGRESS_MAX)));
  }
}
