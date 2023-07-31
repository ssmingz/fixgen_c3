class PlaceHold {
  public Void forBitwiseOrExpressionDoFirst(BitwiseOrExpression that) {
    _addAndIgnoreError(
        "Bitwise or expressions cannot be used at any language level."
            + "  Perhaps you meant to compare two values using regular or (||)",
        that);
    return null;
  }
}
