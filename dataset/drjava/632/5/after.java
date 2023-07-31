class PlaceHold {
  public Void forBreakStatementDoFirst(BreakStatement that) {
    _addError("Break statements cannot be used at the Elementary level", that);
    return null;
  }
}
