class PlaceHold {
  void headerOnMouseDown(Event event) {
    if (event.button != 1) {
      return;
    }
    TableColumn[] columns = getOrderedColumns();
    for (int i = 0; i < columns.length; i++) {
      TableColumn column = columns[i];
      int x = column.getX() + column.width;
      if (Math.abs(x - event.x) <= TOLLERANCE_COLUMNRESIZE) {
        if (!column.resizable) {
          return;
        }
        resizeColumn = column;
        resizeColumnX = x;
        return;
      }
      if (event.x < x) {
        Event newEvent = new Event();
        newEvent.widget = column;
        column.postEvent(Selection, newEvent);
        return;
      }
    }
  }
}
