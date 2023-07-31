class PlaceHold {
  void mouseDown(int id, int sel, int theEvent) {
    super.mouseDown(id, sel, theEvent);
    if (id == headerView.id) {
      NSEvent nsEvent = new NSEvent(theEvent);
      NSPoint location = nsEvent.locationInWindow();
      location = headerView.convertPoint_fromView_(location, null);
      int index = ((int) (headerView.columnAtPoint(location)));
      if (index != (-1)) {
        if ((style & SWT.CHECK) != 0) {
          index--;
        }
        TableColumn column = columns[index];
        column.postEvent(Selection);
      }
    }
  }
}
