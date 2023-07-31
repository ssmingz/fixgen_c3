class PlaceHold {
  public void append(Path other) {
    String[] l = other.list();
    for (int i = 0; i < l.length; i++) {
      if (elements.indexOf(l[i]) == (-1)) {
        elements.addElement(l[i]);
      }
    }
  }
}
