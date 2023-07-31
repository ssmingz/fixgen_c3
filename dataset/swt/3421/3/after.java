class PlaceHold {
  void updateHorizontalBar() {
    ScrollBar hBar = getHorizontalBar();
    int maxX = 0;
    if (columns.length > 0) {
      for (int i = 0; i < columns.length; i++) {
        maxX += columns[i].width;
      }
    } else {
      for (int i = 0; i < itemsCount; i++) {
        Rectangle itemBounds = items[i].getBounds();
        maxX = Math.max(maxX, (itemBounds.x + itemBounds.width) + horizontalOffset);
      }
    }
    int clientWidth = getClientArea().width;
    if (maxX != hBar.getMaximum()) {
      hBar.setMaximum(maxX);
    }
    int thumb = Math.min(clientWidth, maxX);
    if (thumb != hBar.getThumb()) {
      hBar.setThumb(thumb);
      hBar.setPageIncrement(thumb);
    }
    hBar.setVisible(clientWidth < maxX);
    if (maxX < (horizontalOffset + thumb)) {
      horizontalOffset = maxX - thumb;
      hBar.setSelection(horizontalOffset);
      redraw();
    } else {
      int selection = hBar.getSelection();
      if (selection != horizontalOffset) {
        horizontalOffset = selection;
        redraw();
      }
    }
  }
}
