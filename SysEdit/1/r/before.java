class PlaceHold {
  org.eclipse.compare.contentmergeviewer.Position getPosition(char type) {
    switch (type) {
      case 'A':
        return fAncestorPos;
      case 'L':
        return fLeftPos;
      case 'R':
        return fRightPos;
    }
    return null;
  }
}
