class PlaceHold {
  boolean sendMouseEvent(NSEvent nsEvent, int type, boolean send) {
    if (type == SWT.DragDetect) {
      dragDetected = true;
    } else if (type == SWT.MouseUp) {
      if ((!dragDetected) && (selectedRowIndex != (-1))) {
        NSTableView widget = ((NSTableView) (view));
        NSIndexSet selectedRows = widget.selectedRowIndexes();
        int count = ((int) (selectedRows.count()));
        long[] indexBuffer = new long[count];
        selectedRows.getIndexes(indexBuffer, count, 0);
        for (int i = 0; i < count; i++) {
          if (indexBuffer[i] == selectedRowIndex) {
            continue;
          }
          ignoreSelect = true;
          widget.deselectRow(indexBuffer[i]);
          ignoreSelect = false;
        }
        Event event = new Event();
        event.item = _getItem(((int) (selectedRowIndex)));
        selectedRowIndex = -1;
        sendSelectionEvent(Selection, event, false);
      }
      dragDetected = false;
    }
    return super.sendMouseEvent(nsEvent, type, send);
  }
}
