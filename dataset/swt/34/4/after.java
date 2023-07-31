class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (enabled == getEnabled()) {
      return;
    }
    super.setEnabled(enabled);
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    if (enabled && OS.Window_IsActive(shellHandle)) {
      if (!restoreFocus()) {
        traverseGroup(true);
      }
    }
  }
}
