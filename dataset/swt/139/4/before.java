class PlaceHold {
  public boolean forceFocus() {
    checkWidget();
    Decorations shell = menuShell();
    shell.setSavedFocus(this);
    shell.bringToTop();
    return OS.XmProcessTraversal(handle, XmTRAVERSE_CURRENT);
  }
}
