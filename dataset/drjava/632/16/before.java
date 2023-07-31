class PlaceHold {
  public void forLabeledStatementDoFirst(LabeledStatement that) {
    _addError("Labeled statements cannot be used at the Elementary level", that);
  }
}
