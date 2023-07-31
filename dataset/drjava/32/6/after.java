class PlaceHold {
  public Vector<StateBlock> insertClosedParen() {
    _insertBrace(")");
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
