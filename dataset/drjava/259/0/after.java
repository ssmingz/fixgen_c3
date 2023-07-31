class PlaceHold {
  public static TypeName makeEmptyTypeName(Node location) {
    return new TypeName(location.getSourceInfo()) {
      public <T> T acceptVisitor(Visitor<T> v) {
        throw new IllegalArgumentException("Cannot visit an empty type name");
      }
    };
  }
}
