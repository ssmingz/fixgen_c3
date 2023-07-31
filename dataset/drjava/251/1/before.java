class PlaceHold {
  public Breakpoint getBreakpoint(int line, String className) {
    for (int i = 0; i < _breakpoints.size(); i++) {
      Breakpoint bp = _breakpoints.elementAt(i);
      if ((bp.getLineNumber() == line) && bp.getClassName().equals(className)) {
        return bp;
      }
    }
    return null;
  }
}
