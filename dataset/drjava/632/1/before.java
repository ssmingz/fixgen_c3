class PlaceHold {
  public void forLabeledBreakStatementDoFirst(LabeledBreakStatement that) {
    _addError(
        "Labeled statements cannot be used at the Advanced level, so you cannot break to a label",
        that);
  }
}
