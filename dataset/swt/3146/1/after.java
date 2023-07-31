class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemsCount))) {
      return;
    }
    int visibleItemCount = (clientArea.height - getHeaderHeight()) / itemHeight;
    if (itemsCount <= visibleItemCount) {
      return;
    }
    index = Math.min(index, itemsCount - visibleItemCount);
    if (index == topIndex) {
      return;
    }
    update();
    int change = topIndex - index;
    topIndex = index;
    getVerticalBar().setSelection(topIndex);
    if (drawCount <= 0) {
      GC gc = new GC(this);
      gc.copyArea(0, 0, clientArea.width, clientArea.height, 0, change * itemHeight);
      gc.dispose();
    }
  }
}
