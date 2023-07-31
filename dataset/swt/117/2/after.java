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
    if (!isReparentable()) {
      return false;
    }
    OS.gtk_widget_realize(parent.handle);
    int topHandle = topHandle();
    int x = OS.GTK_WIDGET_X(topHandle);
    int width = ((state & ZERO_WIDTH) != 0) ? 0 : OS.GTK_WIDGET_WIDTH(topHandle);
    if ((this.parent.style & SWT.MIRRORED) != 0) {
      x = (this.parent.getClientWidth() - width) - x;
    }
    if ((parent.style & SWT.MIRRORED) != 0) {
      x = (parent.getClientWidth() - width) - x;
    }
    int y = OS.GTK_WIDGET_Y(topHandle);
    releaseParent();
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
    int newParent = parent.parentingHandle();
    OS.gtk_widget_reparent(topHandle, newParent);
    OS.gtk_fixed_move(newParent, topHandle, x, y);
    this.parent = parent;
    setZOrder(null, false, true);
    return true;
  }
}
