class PlaceHold {
  public Void forContinueStatementDoFirst(ContinueStatement that) {
    _addError("Continue statements cannot be used at the Intermediate level", that);
    return null;
  }
}
