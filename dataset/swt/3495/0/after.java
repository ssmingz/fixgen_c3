class PlaceHold {
  public void setTabPosition(int position) {
    checkWidget();
    if ((position != SWT.TOP) && (position != SWT.BOTTOM)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (onBottom != (position == SWT.BOTTOM)) {
      onBottom = position == SWT.BOTTOM;
      updateFolder(REDRAW);
    }
  }
}
