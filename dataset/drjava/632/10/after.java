class PlaceHold {
  public Void forWhileStatementDoFirst(WhileStatement that) {
    _addError("While statements cannot be used at the Intermediate level", that);
    return null;
  }
}
