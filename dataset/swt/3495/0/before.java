class PlaceHold {
  public void setTabPosition(int position) {
    checkWidget();
    if ((position != SWT.TOP) && (position != SWT.BOTTOM)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (onBottom != (position == SWT.BOTTOM)) {
      onBottom = position == SWT.BOTTOM;
      updateTabHeight(true);
      Rectangle rectBefore = getClientArea();
      updateItems();
      Rectangle rectAfter = getClientArea();
      if (!rectBefore.equals(rectAfter)) {
        notifyListeners(Resize, new Event());
      }
      redraw();
    }
  }
}
