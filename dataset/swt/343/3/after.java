class PlaceHold {
  void setItemsSorted(Combo combo, Hashtable items) {
    if (items == null) {
      return;
    }
    Enumeration itemKeys = items.keys();
    String[] sortedItems = new String[items.size()];
    int index = 0;
    while (itemKeys.hasMoreElements()) {
      String item = ((String) (itemKeys.nextElement()));
      if (item.length() != 0) {
        sortedItems[index++] = item;
      }
    }
    if (index != sortedItems.length) {
      String[] newItems = new String[index];
      System.arraycopy(sortedItems, 0, newItems, 0, index);
      sortedItems = newItems;
    }
    sort(sortedItems);
    combo.setItems(sortedItems);
  }
}
