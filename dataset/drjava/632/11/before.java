class PlaceHold {
  public void forForStatementDoFirst(ForStatement that) {
    _addError("For statements cannot be used at the Intermediate level", that);
  }
}
