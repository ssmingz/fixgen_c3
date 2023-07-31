class PlaceHold {
  public void forLabeledContinueStatementDoFirst(LabeledContinueStatement that) {
    _addError(
        "Labeled statements cannot be used at the Advanced level, so you cannot use a labeled"
            + " continue statement",
        that);
  }
}
