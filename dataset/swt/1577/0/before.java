class PlaceHold {
  void mouseDownSuper(int id, int sel, int theEvent) {
    NSOutlineView widget = ((NSOutlineView) (view));
    NSEvent nsEvent = new NSEvent(theEvent);
    NSPoint pt = view.convertPoint_fromView_(nsEvent.locationInWindow(), null);
    int row = ((int) (widget.rowAtPoint(pt)));
    if ((row != (-1))
        && ((nsEvent.modifierFlags() & OS.NSDeviceIndependentModifierFlagsMask) == 0)) {
      if (widget.isRowSelected(row)) {
        NSRect rect = widget.frameOfOutlineCellAtRow(row);
        if (!OS.NSPointInRect(pt, rect)) {
          id itemID = widget.itemAtRow(row);
          Widget item = (itemID != null) ? display.getWidget(itemID.id) : null;
          if ((item != null) && (item instanceof TreeItem)) {
            Event event = new Event();
            event.item = item;
            sendSelectionEvent(Selection, event, false);
          }
        }
      }
    }
    didSelect = false;
    super.mouseDownSuper(id, sel, theEvent);
    didSelect = false;
  }
}
