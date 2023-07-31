class PlaceHold {
  public String toString() {
    StringBuffer buf = new StringBuffer();
    final int size = triggers.size();
    for (int i = 0; i < size; i++) {
      buf.append(triggers.elementAt(i).toString());
      if (i < (size - 1)) {
        buf.append(',');
      }
    }
    return buf.toString();
  }
}
