class PlaceHold {
  void headerDoMouseDown(Event event) {
    if (event.button != 1) {
      return;
    }
    for (int i = 0; i < columns.length; i++) {
      TreeColumn column = columns[i];
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
        sendEvent(Selection, newEvent);
        return;
      }
    }
  }
}
