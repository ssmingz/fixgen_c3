class PlaceHold {
  public Vector<StateBlock> insertOpenBracket() {
    _insertBrace("[");
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
