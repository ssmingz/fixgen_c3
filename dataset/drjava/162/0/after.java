class PlaceHold {
  public static Expression makeEmptyExpression() {
    return new Expression(SourceInfo.NONE) {
      public <T> T acceptVisitor(Visitor<T> v) {
        throw new IllegalArgumentException("Cannot visit an empty expression");
      }
    };
  }
}
