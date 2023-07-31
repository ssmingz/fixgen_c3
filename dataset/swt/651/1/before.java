class PlaceHold {
  void _setVisible(boolean visible) {
    if ((style & (SWT.BAR | SWT.DROP_DOWN)) != 0) {
      return;
    }
    TrayItem trayItem = display.currentTrayItem;
    if ((trayItem != null) && visible) {
      trayItem.showMenu(this);
      return;
    }
    if (visible) {
      Shell shell = getShell();
      NSWindow window = shell.view.window();
      NSPoint location = null;
      if (hasLocation) {
        NSView topView = window.contentView();
        Point shellCoord = display.map(null, shell, new Point(x, y));
        location = new NSPoint();
        float scaleFactor = window.userSpaceScaleFactor();
        location.x = shellCoord.x * scaleFactor;
        location.y = (topView.frame().height - shellCoord.y) * scaleFactor;
      } else {
        location = window.mouseLocationOutsideOfEventStream();
      }
      hasLocation = false;
      window.retain();
      NSEvent nsEvent =
          NSEvent.otherEventWithType(
              NSApplicationDefined,
              location,
              0,
              0.0,
              window.windowNumber(),
              window.graphicsContext(),
              ((short) (0)),
              0,
              0);
      NSMenu.popUpContextMenu(nsMenu, nsEvent, shell.view);
      window.release();
    } else {
      nsMenu.cancelTracking();
    }
  }
}
