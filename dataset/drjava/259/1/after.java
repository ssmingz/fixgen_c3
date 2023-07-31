class PlaceHold {
  public static Expression makeEmptyExpression(Node location) {
    return new Expression(location.getSourceInfo()) {
      public <T> T acceptVisitor(Visitor<T> v) {
        throw new IllegalArgumentException("Cannot visit an empty expression");
      }
    };
  }
}
