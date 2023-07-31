class PlaceHold {
  public void setTopRight(Control control, int alignment) {
    checkWidget();
    if (((alignment != SWT.RIGHT) && (alignment != SWT.FILL))
        && (alignment != (SWT.RIGHT | SWT.WRAP))) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((control != null) && (control.isDisposed() || (control.getParent() != this))) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((topRight == control) && (topRightAlignment == alignment)) {
      return;
    }
    if ((topRight != null) && (!topRight.isDisposed())) {
      removeTabControl(topRight, false);
    }
    topRight = control;
    topRightAlignment = alignment;
    alignment &= ~SWT.RIGHT;
    if (control != null) {
      addTabControl(control, SWT.TRAIL | alignment, -1, false);
    }
    updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
  }
}
