class PlaceHold {
  public void dragOver(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    Table table = ((Table) (((DropTarget) (event.widget)).getControl()));
    int effect = checkEffect(event.feedback);
    TableItem item = ((TableItem) (getItem(table, event.x, event.y)));
    if ((effect & DND.FEEDBACK_SCROLL) == 0) {
      scrollBeginTime = 0;
      scrollItem = null;
    } else if (((item != null) && item.equals(scrollItem)) && (scrollBeginTime != 0)) {
      if (System.currentTimeMillis() >= scrollBeginTime) {
        Rectangle area = table.getClientArea();
        int headerHeight = table.getHeaderHeight();
        int itemHeight = table.getItemHeight();
        Point pt = new Point(event.x, event.y);
        pt = table.getDisplay().map(null, table, pt);
        TableItem nextItem = null;
        if (pt.y < ((area.y + headerHeight) + (2 * itemHeight))) {
          int index = Math.max(0, table.indexOf(item) - 1);
          nextItem = table.getItem(index);
        }
        if (pt.y > ((area.y + area.height) - (2 * itemHeight))) {
          int index = Math.min(table.getItemCount() - 1, table.indexOf(item) + 1);
          nextItem = table.getItem(index);
        }
        if (nextItem != null) {
          table.showItem(nextItem);
        }
        scrollBeginTime = 0;
        scrollItem = null;
      }
    } else {
      scrollBeginTime = System.currentTimeMillis() + SCROLL_HYSTERESIS;
      scrollItem = item;
    }
    ((DropTarget) (event.widget)).feedback = effect;
  }
}
