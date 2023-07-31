class PlaceHold {
  public synchronized boolean isSelected(Resource r) {
    int passed = 0;
    int failed = 0;
    int count = selectorCount();
    boolean even = (count % 2) == 0;
    int threshold = count / 2;
    for (Iterator<ResourceSelector> i = getSelectors(); i.hasNext(); ) {
      if (i.next().isSelected(r)) {
        ++passed;
        if ((passed > threshold) || ((even && tie) && (passed == threshold))) {
          return true;
        }
      } else {
        ++failed;
        if ((failed > threshold) || ((even && (!tie)) && (failed == threshold))) {
          return false;
        }
      }
    }
    return false;
  }
}
