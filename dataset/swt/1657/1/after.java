class PlaceHold {
  void headerOnMouseDown(Event event) {
    if (event.button != 1) {
      return;
    }
    TableColumn[] orderedColumns = getOrderedColumns();
    int x = -horizontalOffset;
    for (int i = 0; i < orderedColumns.length; i++) {
      TableColumn column = orderedColumns[i];
      x += column.width;
      if (column.resizable && (Math.abs(x - event.x) <= TOLLERANCE_COLUMNRESIZE)) {
        resizeColumn = column;
        resizeColumnX = x;
        return;
      }
      if (event.x < x) {
        if (column.moveable) {
          int columnX = column.getX();
          int pointerOffset = event.x - columnX;
          headerHideToolTip();
          Tracker tracker = new Tracker(this, SWT.NONE);
          tracker.setRectangles(
              new Rectangle[] {new Rectangle(columnX, 0, column.width, getHeaderHeight())});
          if (!tracker.open()) {
            return;
          }
          Rectangle result = tracker.getRectangles()[0];
          int pointerX = result.x + pointerOffset;
          if (pointerX < 0) {
            return;
          }
          x = -horizontalOffset;
          for (int destIndex = 0; destIndex < orderedColumns.length; destIndex++) {
            TableColumn destColumn = orderedColumns[destIndex];
            x += destColumn.width;
            if (pointerX < x) {
              int oldIndex = column.getOrderIndex();
              if (destIndex == oldIndex) {
                Event newEvent = new Event();
                newEvent.widget = column;
                column.postEvent(Selection, newEvent);
                return;
              }
              int leftmostIndex = Math.min(destIndex, oldIndex);
              int[] oldOrder = getColumnOrder();
              int[] newOrder = new int[oldOrder.length];
              System.arraycopy(oldOrder, 0, newOrder, 0, leftmostIndex);
              if (leftmostIndex == oldIndex) {
                System.arraycopy(oldOrder, oldIndex + 1, newOrder, oldIndex, destIndex - oldIndex);
              } else {
                System.arraycopy(
                    oldOrder, destIndex, newOrder, destIndex + 1, oldIndex - destIndex);
              }
              newOrder[destIndex] = oldOrder[oldIndex];
              int rightmostIndex = Math.max(destIndex, oldIndex);
              System.arraycopy(
                  oldOrder,
                  rightmostIndex + 1,
                  newOrder,
                  rightmostIndex + 1,
                  (newOrder.length - rightmostIndex) - 1);
              setColumnOrder(newOrder);
              return;
            }
          }
          return;
        }
        Event newEvent = new Event();
        newEvent.widget = column;
        column.postEvent(Selection, newEvent);
        return;
      }
    }
  }
}
