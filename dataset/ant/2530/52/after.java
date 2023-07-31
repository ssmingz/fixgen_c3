class PlaceHold {
  public String toString() {
    StringBuffer buf = new StringBuffer();
    final int size = filters.size();
    if (defaultExclude) {
      buf.append(DEFAULT_EXCLUDE);
      if (size > 0) {
        buf.append(',');
      }
    }
    for (int i = 0; i < size; i++) {
      buf.append(filters.get(i).toString());
      if (i < (size - 1)) {
        buf.append(',');
      }
    }
    return buf.toString();
  }
}
