class PlaceHold {
  public boolean forceFocus() {
    checkWidget();
    Decorations shell = menuShell();
    shell.setSavedFocus(this);
    shell.bringToTop(false);
    return XmProcessTraversal(focusHandle(), XmTRAVERSE_CURRENT);
  }
}
