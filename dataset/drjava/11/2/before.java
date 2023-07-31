class PlaceHold {
  public void setSearchBackwards(boolean searchBackwards) {
    if (_isForward == searchBackwards) {
      if (isOnMatch() && _findWord.equals(_lastFindWord)) {
        _skipText = true;
      } else {
        _skipText = false;
      }
    }
    _isForward = !searchBackwards;
  }
}
