class PlaceHold {
  public boolean setFocus() {
    checkWidget();
    Decorations shell = menuShell();
    shell.setSavedFocus(this);
    shell.bringToTop(false);
    return OS.XmProcessTraversal(handle, XmTRAVERSE_CURRENT);
  }
}
