class PlaceHold {
  public void forBitwiseAndExpressionDoFirst(BitwiseAndExpression that) {
    _addAndIgnoreError(
        "Bitwise and expressions cannot be used at any language level."
            + "  Perhaps you meant to compare two values using regular and (&&)",
        that);
  }
}
