class PlaceHold {
  @Override
  public DetailNode[] getChildren() {
    if (children == null) {
      return null;
    } else {
      return Arrays.copyOf(children, children.length);
    }
  }
}
