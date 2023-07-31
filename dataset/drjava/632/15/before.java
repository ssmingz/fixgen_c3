class PlaceHold {
  public void forContinueStatementDoFirst(ContinueStatement that) {
    _addError("Continue statements cannot be used at the Intermediate level", that);
  }
}
