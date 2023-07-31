class PlaceHold {
  private String mapElement(String elem) throws TaskException {
    int size = prefixMap.size();
    if (size != 0) {
      for (int i = 0; i < size; i++) {
        MapEntry entry = ((MapEntry) (prefixMap.elementAt(i)));
        String newElem = entry.apply(elem);
        if (newElem != elem) {
          elem = newElem;
          break;
        }
      }
    }
    return elem;
  }
}
