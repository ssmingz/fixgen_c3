class PlaceHold {
  public boolean setParent(Composite parent) {
    checkWidget();
    if (parent == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (parent.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.parent == parent) {
      return true;
    }
    releaseChild();
    Shell newShell = parent.getShell();
    Shell oldShell = getShell();
    Decorations newDecorations = parent.menuShell();
    Decorations oldDecorations = menuShell();
    Menu[] menus = oldShell.findMenus(this);
    if ((oldShell != newShell) || (oldDecorations != newDecorations)) {
      fixChildren(newShell, oldShell, newDecorations, oldDecorations, menus);
      newDecorations.fixAccelGroup();
      oldDecorations.fixAccelGroup();
    }
    int topHandle = topHandle();
    int newParent = parent.parentingHandle();
    int x = OS.GTK_WIDGET_X(topHandle);
    int y = OS.GTK_WIDGET_Y(topHandle);
    OS.gtk_widget_reparent(topHandle, newParent);
    OS.gtk_fixed_move(newParent, topHandle, x, y);
    this.parent = parent;
    return true;
  }
}
