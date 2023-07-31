class PlaceHold {
  public void forBitwiseNotExpressionDoFirst(BitwiseNotExpression that) {
    _addAndIgnoreError(
        "Bitwise not expressions cannot be used at any language level."
            + "  Perhaps you meant to negate this value using regular not (!)",
        that);
  }
}
