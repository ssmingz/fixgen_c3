class PlaceHold {
  public static Expression makeEmptyExpression() {
    return new Expression(null, 0, 0, 0, 0) {
      public <T> T acceptVisitor(Visitor<T> v) {
        throw new IllegalArgumentException("Cannot visit an empty expression");
      }
    };
  }
}
