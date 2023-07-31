class PlaceHold {
  public Vector<StateBlock> insertOpenSquiggly() {
    _insertBrace("{");
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
