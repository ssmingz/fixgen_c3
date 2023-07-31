class PlaceHold {
  void dropDown(boolean drop) {
    if (drop == isDropped()) {
      return;
    }
    if (!drop) {
      popup.setVisible(false);
      text.setFocus();
      return;
    }
    int index = list.getSelectionIndex();
    if (index != (-1)) {
      list.setTopIndex(index);
    }
    Rectangle listRect = list.getBounds();
    int borderWidth = getBorderWidth();
    Point point = toDisplay(new Point(0 - borderWidth, 0 - borderWidth));
    Point comboSize = getSize();
    popup.setBounds(point.x, point.y + comboSize.y, comboSize.x, listRect.height + 2);
    popup.setVisible(true);
    list.setFocus();
  }
}
