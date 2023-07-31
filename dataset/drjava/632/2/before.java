class PlaceHold {
  public void forContinueStatementDoFirst(ContinueStatement that) {
    _addError("Continue statements cannot be used at the Elementary level", that);
  }
}
