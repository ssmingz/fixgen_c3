class PlaceHold {
  public int suggestedChildLevel(ExpressionHandler aChild) {
    return getLevel() + mIndentCheck.getIndentationAmount();
  }
}
