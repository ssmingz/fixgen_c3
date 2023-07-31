class PlaceHold {
  public Void forDoStatementDoFirst(DoStatement that) {
    _addError("Do statements cannot be used at the Elementary level", that);
    return null;
  }
}
