class PlaceHold {
  public void setChecked(boolean checked) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    if (this.checked == checked) {
      return;
    }
    this.checked = checked;
    cached = true;
    ((NSOutlineView) (parent.view)).reloadItem(handle);
  }
}
