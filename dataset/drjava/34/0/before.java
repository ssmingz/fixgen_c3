class PlaceHold {
  public synchronized boolean posInParenPhrase(int pos) {
    String key = "posInParenPhrase:" + pos;
    Boolean cached = ((Boolean) (_checkCache(key)));
    if (cached != null) {
      return cached.booleanValue();
    }
    int here = _currentLocation;
    _reduced.move(pos - here);
    boolean inParenPhrase = posInParenPhrase();
    _reduced.move(here - pos);
    _storeInCache(key, Boolean.valueOf(inParenPhrase));
    return inParenPhrase;
  }
}
