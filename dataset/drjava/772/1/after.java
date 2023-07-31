class PlaceHold {
  public Vector<StateBlock> insertClosedBracket() {
    _insertBrace("]");
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
