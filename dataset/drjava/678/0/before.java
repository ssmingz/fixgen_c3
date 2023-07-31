class PlaceHold {
  public String getPackageName() throws InvalidPackageException {
    StringBuffer buf = new StringBuffer();
    int oldLocation = getCurrentLocation();
    try {
      setCurrentLocation(0);
      final int docLength = getLength();
      final String text = getText(0, docLength);
      int firstNormalLocation = 0;
      while (firstNormalLocation < docLength) {
        setCurrentLocation(firstNormalLocation);
        if (_reduced.currentToken().getHighlightState() == HighlightStatus.NORMAL) {
          char curChar = text.charAt(firstNormalLocation);
          if (!Character.isWhitespace(curChar)) {
            break;
          }
        }
        firstNormalLocation++;
      }
      if (firstNormalLocation == docLength) {
        return "";
      }
      final int strlen = "package".length();
      final int endLocation = firstNormalLocation + strlen;
      if (((firstNormalLocation + strlen) > docLength)
          || (!text.substring(firstNormalLocation, endLocation).equals("package"))) {
        return "";
      }
      int afterPackage = firstNormalLocation + "package".length();
      int semicolonLocation = afterPackage;
      do {
        semicolonLocation = text.indexOf(";", semicolonLocation + 1);
        if (semicolonLocation == (-1)) {
          throw new InvalidPackageException(
              firstNormalLocation, "No semicolon found to terminate " + "package statement!");
        }
        setCurrentLocation(semicolonLocation);
      } while (_reduced.currentToken().getHighlightState() != HighlightStatus.NORMAL);
      for (int walk = afterPackage + 1; walk < semicolonLocation; walk++) {
        setCurrentLocation(walk);
        if (_reduced.currentToken().getHighlightState() == HighlightStatus.NORMAL) {
          char curChar = text.charAt(walk);
          if (!Character.isWhitespace(curChar)) {
            buf.append(curChar);
          }
        }
      }
      String toReturn = buf.toString();
      if (toReturn.equals("")) {
        throw new InvalidPackageException(
            firstNormalLocation, "Package name was not specified " + "after the package keyword!");
      }
      return toReturn;
    } catch (BadLocationException ble) {
      throw new UnexpectedException(ble);
    } finally {
      setCurrentLocation(oldLocation);
    }
  }
}
