class PlaceHold {
  public void forThrowStatementDoFirst(ThrowStatement that) {
    _addError("Throw statements cannot be used at the Elementary level", that);
  }
}
