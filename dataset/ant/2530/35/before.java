class PlaceHold {
  public Node item(int i) {
    try {
      return ((Node) (elementAt(i)));
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }
}
