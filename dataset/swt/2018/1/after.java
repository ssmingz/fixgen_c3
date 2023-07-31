class PlaceHold {
  public void dragOver(DropTargetEvent event) {
    Tree tree = ((Tree) (control));
    int effect = checkEffect(event.feedback);
    TreeItem item = ((TreeItem) (getItem(tree, event.x, event.y)));
    if ((effect & DND.FEEDBACK_EXPAND) == 0) {
      expandBeginTime = 0;
      expandItem = null;
    } else if (((item != null) && item.equals(expandItem)) && (expandBeginTime != 0)) {
      if (System.currentTimeMillis() >= expandBeginTime) {
        if ((item.getItemCount() > 0) && (!item.getExpanded())) {
          Event e = new Event();
          e.x = event.x;
          e.y = event.y;
          e.item = item;
          e.time = ((int) (System.currentTimeMillis()));
          tree.notifyListeners(Expand, e);
          if (item.isDisposed()) {
            return;
          }
          item.setExpanded(true);
        }
        expandBeginTime = 0;
        expandItem = null;
      }
    } else {
      expandBeginTime = System.currentTimeMillis() + EXPAND_HYSTERESIS;
      expandItem = item;
    }
    if ((effect & DND.FEEDBACK_SCROLL) == 0) {
      scrollBeginTime = 0;
      scrollItem = null;
    } else if (((item != null) && item.equals(scrollItem)) && (scrollBeginTime != 0)) {
      if (System.currentTimeMillis() >= scrollBeginTime) {
        Rectangle area = tree.getClientArea();
        int headerHeight = tree.getHeaderHeight();
        int itemHeight = tree.getItemHeight();
        Point pt = new Point(event.x, event.y);
        pt = tree.getDisplay().map(null, tree, pt);
        TreeItem nextItem = null;
        if (pt.y < ((area.y + headerHeight) + (2 * itemHeight))) {
          nextItem = previousItem(tree, item);
        }
        if (pt.y > ((area.y + area.height) - (2 * itemHeight))) {
          nextItem = nextItem(tree, item);
        }
        if (nextItem != null) {
          tree.showItem(nextItem);
        }
        scrollBeginTime = 0;
        scrollItem = null;
      }
    } else {
      scrollBeginTime = System.currentTimeMillis() + SCROLL_HYSTERESIS;
      scrollItem = item;
    }
    if ((effect & DND.FEEDBACK_SELECT) != 0) {
      if ((currentItem != item) || ((currentEffect & DND.FEEDBACK_SELECT) == 0)) {
        setDropSelection(tree, item);
        currentEffect = effect;
        currentItem = item;
      }
    } else {
      setDropSelection(tree, null);
    }
    if (((effect & DND.FEEDBACK_INSERT_AFTER) != 0)
        || ((effect & DND.FEEDBACK_INSERT_BEFORE) != 0)) {
      if (((currentItem != item)
              || ((effect & DND.FEEDBACK_INSERT_AFTER)
                  != (currentEffect & DND.FEEDBACK_INSERT_AFTER)))
          || ((effect & DND.FEEDBACK_INSERT_BEFORE)
              != (currentEffect & DND.FEEDBACK_INSERT_BEFORE))) {
        setInsertMark(tree, item, (effect & DND.FEEDBACK_INSERT_BEFORE) != 0);
        currentEffect = effect;
        currentItem = item;
      }
    } else {
      setInsertMark(tree, null, false);
    }
  }
}
