class PlaceHold {
  public boolean setParent(Composite parent) {
    checkWidget();
    if (parent == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (parent.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.parent == parent) {
      return true;
    }
    if (!isReparentable()) {
      return false;
    }
    releaseChild();
    Shell newShell = parent.getShell();
    Shell oldShell = getShell();
    Decorations newDecorations = parent.menuShell();
    Decorations oldDecorations = menuShell();
    Menu[] menus = oldShell.findMenus(this);
    if ((oldShell != newShell) || (oldDecorations != newDecorations)) {
      fixChildren(newShell, oldShell, newDecorations, oldDecorations, menus);
    }
    if (OS.SetParent(handle, parent.handle) == 0) {
      return false;
    }
    this.parent = parent;
    int flags = (OS.SWP_NOSIZE | OS.SWP_NOMOVE) | OS.SWP_NOACTIVATE;
    SetWindowPos(handle, HWND_BOTTOM, 0, 0, 0, 0, flags);
    return true;
  }
}
