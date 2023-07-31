class PlaceHold {
  public boolean replaceCurrent() {
    if (!isOnMatch()) {
      return false;
    }
    _doc.modifyLock();
    try {
      int offset = getCurrentOffset();
      if (_isForward) {
        offset -= _findWord.length();
      }
      _doc.remove(offset, _findWord.length());
      _doc.insertString(getCurrentOffset(), _replaceWord, null);
      if (_isForward) {
        setPosition(offset + _replaceWord.length());
      } else {
        setPosition(offset);
      }
      return true;
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    } finally {
      _doc.modifyUnlock();
    }
  }
}
