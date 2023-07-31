class PlaceHold {
  void mouseDownSuper(int id, int sel, int theEvent) {
    NSTableView widget = ((NSTableView) (view));
    NSEvent nsEvent = new NSEvent(theEvent);
    NSPoint pt = view.convertPoint_fromView_(nsEvent.locationInWindow(), null);
    int row = ((int) (widget.rowAtPoint(pt)));
    if ((row != (-1))
        && ((nsEvent.modifierFlags() & OS.NSDeviceIndependentModifierFlagsMask) == 0)) {
      if (widget.isRowSelected(row)) {
        if ((0 <= row) && (row < itemCount)) {
          Event event = new Event();
          event.item = _getItem(((int) (row)));
          sendSelectionEvent(Selection, event, false);
        }
      }
    }
    didSelect = false;
    super.mouseDownSuper(id, sel, theEvent);
    didSelect = false;
  }
}
