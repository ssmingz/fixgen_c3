class PlaceHold {
  void destroyItem(ToolItem item) {
    int index = 0;
    while (index < itemCount) {
      if (items[index] == item) {
        break;
      }
      index++;
    }
    if (index == itemCount) {
      return;
    }
    System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
    items[itemCount] = null;
  }
}
