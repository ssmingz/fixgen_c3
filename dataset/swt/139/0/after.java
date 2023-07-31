class PlaceHold {
  void closeWidget() {
    if (!isEnabled()) {
      return;
    }
    Control widget = parent;
    while ((widget != null) && (!widget.getShell().isModal())) {
      widget = widget.parent;
    }
    if (widget == null) {
      Shell[] shells = getShells();
      for (int i = 0; i < shells.length; i++) {
        Shell shell = shells[i];
        if (((shell != this) && shell.isModal()) && shell.isVisible()) {
          shell.bringToTop(false);
          return;
        }
      }
    }
    Event event = new Event();
    sendEvent(Close, event);
    if (event.doit && (!isDisposed())) {
      dispose();
    }
  }
}
