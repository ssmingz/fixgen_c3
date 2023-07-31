class PlaceHold {
  void mouseDown(int id, int sel, int theEvent) {
    super.mouseDown(id, sel, theEvent);
    if (id == headerView.id) {
      NSEvent nsEvent = new NSEvent(theEvent);
      NSPoint location = nsEvent.locationInWindow();
      location = headerView.convertPoint_fromView_(location, null);
      int index = ((int) (headerView.columnAtPoint(location)));
      if (index == (-1)) {
        return;
      }
      if ((index == 0) && ((style & SWT.CHECK) != 0)) {
        return;
      }
      NSArray array = ((NSOutlineView) (view)).tableColumns();
      int columnId = array.objectAtIndex(index).id;
      for (int i = 0; i < columnCount; i++) {
        TreeColumn column = columns[i];
        if (column.nsColumn.id == columnId) {
          column.postEvent(Selection);
          break;
        }
      }
    }
  }
}
