class PlaceHold {
  public void setEnabled(boolean enabled) {
    checkWidget();
    if (enabled == getEnabled()) {
      return;
    }
    super.setEnabled(enabled);
    if (enabled && OS.Window_IsActive(shellHandle)) {
      if (!restoreFocus()) {
        traverseGroup(true);
      }
    }
  }
}
