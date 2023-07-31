class PlaceHold {
  protected boolean couldHoldIncluded(String name) {
    for (int i = 0; i < includes.length; i++) {
      if (matchPatternStart(includes[i], name)) {
        return true;
      }
    }
    return false;
  }
}
