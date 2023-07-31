class PlaceHold {
  public Void forThrowStatementDoFirst(ThrowStatement that) {
    _addError("Throw statements cannot be used at the Elementary level", that);
    return null;
  }
}
