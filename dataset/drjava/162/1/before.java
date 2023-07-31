class PlaceHold {
  public static TypeName makeEmptyTypeName() {
    return new TypeName(null, 0, 0, 0, 0) {
      public <T> T acceptVisitor(Visitor<T> v) {
        throw new IllegalArgumentException("Cannot visit an empty type name");
      }
    };
  }
}
