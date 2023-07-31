class PlaceHold {
  public void forBreakStatementDoFirst(BreakStatement that) {
    _addError("Break statements cannot be used at the Intermediate level", that);
  }
}
