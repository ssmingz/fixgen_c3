class PlaceHold {
  public String toString() {
    StringBuffer val = new StringBuffer();
    int i;
    for (i = 0; i < getSize(); i++) {
      val.append(' ');
      val.append(getType().charAt(i));
    }
    return val.toString();
  }
}
