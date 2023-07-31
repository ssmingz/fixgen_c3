class PlaceHold {
  public boolean setParent(Composite parent) {
    checkWidget();
    if (parent == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (parent.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (OS.SetParent(handle, parent.handle) == 0) {
      return false;
    }
    this.parent = parent;
    return true;
  }
}
