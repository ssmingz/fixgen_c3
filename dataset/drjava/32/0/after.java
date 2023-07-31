class PlaceHold {
  public Vector<StateBlock> insertClosedSquiggly() {
    _insertBrace("}");
    return SBVectorFactory.generate(_cursor.copy(), _offset);
  }
}
