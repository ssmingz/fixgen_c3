class PlaceHold {
  public Void forForStatementDoFirst(ForStatement that) {
    _addError("For statements cannot be used at the Elementary level", that);
    return null;
  }
}
