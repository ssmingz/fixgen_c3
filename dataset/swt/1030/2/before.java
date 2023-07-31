class PlaceHold {
  int computeAvailableDescendentCount() {
    int result = 1;
    if (!isExpanded) {
      return result;
    }
    for (int i = 0; i < items.length; i++) {
      result += items[i].computeAvailableDescendentCount();
    }
    return result;
  }
}
