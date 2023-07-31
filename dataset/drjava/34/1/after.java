class PlaceHold {
  protected synchronized boolean posNotInBlock(int pos) {
    String key = "posNotInBlock:" + pos;
    Boolean cached = ((Boolean) (_checkCache(key)));
    if (cached != null) {
      return cached.booleanValue();
    }
    int here = _currentLocation;
    _reduced.move(pos - here);
    IndentInfo info = _reduced.getIndentInformation();
    boolean notInParenPhrase = info.braceTypeCurrent.equals(noBrace);
    _reduced.move(here - pos);
    _storeInCache(key, new Boolean(notInParenPhrase));
    return notInParenPhrase;
  }
}
