class PlaceHold {
  public Void forLabeledStatementDoFirst(LabeledStatement that) {
    _addError("Labeled statements cannot be used at the Elementary level", that);
    return null;
  }
}
