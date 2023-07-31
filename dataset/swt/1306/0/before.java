class PlaceHold {
  void sendToolTipEvent(boolean enter) {
    if ((tooltipTrackingArea == null) && isVisible()) {
      NSView contentView = window.contentView();
      int tag = contentView.addToolTipRect(new NSRect(), window, 0);
      if (tag != 0) {
        tooltipTrackingArea = new NSTrackingArea(tag);
      }
    }
    if (tooltipTrackingArea == null) {
      return;
    }
    NSDictionary userInfo = tooltipTrackingArea.userInfo();
    NSPoint pt = window.convertScreenToBase(NSEvent.mouseLocation());
    NSEvent event =
        NSEvent.enterExitEventWithType(
            enter ? OS.NSMouseEntered : OS.NSMouseExited,
            pt,
            0,
            0,
            window.windowNumber(),
            null,
            0,
            0,
            userInfo.id);
    if (OS.class_NSToolTipManager != 0) {
      OS.objc_msgSend(
          OS.objc_msgSend(class_NSToolTipManager, sel_sharedToolTipManager),
          enter ? OS.sel_mouseEntered_ : OS.sel_mouseExited_,
          event.id);
    }
  }
}
