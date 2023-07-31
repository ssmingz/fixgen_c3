class PlaceHold {
  public Node item(int i) {
    try {
      return ((Node) (get(i)));
    } catch (ArrayIndexOutOfBoundsException e) {
      return null;
    }
  }
}
