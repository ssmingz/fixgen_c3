class PlaceHold {
  public Vector<StateBlock> insertOpenParen() {
    _insertBrace("(");
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
