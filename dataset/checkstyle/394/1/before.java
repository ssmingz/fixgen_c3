class PlaceHold {
  @Override
  public DetailNode[] getChildren() {
    if (children == null) {
      return children;
    } else {
      return Arrays.copyOf(children, children.length);
    }
  }
}
