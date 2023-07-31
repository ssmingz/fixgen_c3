class PlaceHold {
  void mouseUp(int id, int sel, int theEvent) {
    if (highlight) {
      NSEvent nsEvent = new NSEvent(theEvent);
      if (nsEvent.type() == OS.NSLeftMouseUp) {
        sendSelectionEvent(nsEvent.clickCount() == 2 ? SWT.DefaultSelection : SWT.Selection);
      }
    }
    highlight = false;
    view.setNeedsDisplay(true);
  }
}
