class PlaceHold {
  public String toString() {
    final StringBuilder val = new StringBuilder();
    int i;
    for (i = 0; i < getSize(); i++) {
      val.append(' ');
      val.append(getType().charAt(i));
    }
    return val.toString();
  }
}
