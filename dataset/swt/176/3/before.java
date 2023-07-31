class PlaceHold {
  void calculateItemHeight(SelectableItem item) {
    GC gc;
    String itemText;
    int itemHeight = -1;
    if ((itemImageExtent != null) && (textHeight != (-1))) {
      return;
    }
    itemText = item.getText();
    if ((itemText != null) && (textHeight == (-1))) {
      gc = new GC(this);
      itemHeight = gc.stringExtent(itemText).y;
      textHeight = itemHeight;
      gc.dispose();
    }
    if (itemImageExtent == null) {
      itemImageExtent = getImageExtent(item);
      if (itemImageExtent != null) {
        if (itemImageExtent.y > textHeight) {
          itemHeight = itemImageExtent.y;
        } else {
          itemHeight = textHeight;
        }
      }
    }
    itemHeight += getItemPadding();
    if (itemHeight > getItemHeight()) {
      setItemHeight(itemHeight);
    }
  }
}
