class PlaceHold {
  public void setPosition(int pos) {
    try {
      _current = _doc.createPosition(pos);
    } catch (BadLocationException ble) {
      throw new UnexpectedException(ble);
    }
  }
}
