class PlaceHold {
  public Breakpoint getBreakpointAt(int offset) {
    for (int i = 0; i < _breakpoints.size(); i++) {
      Breakpoint bp = _breakpoints.get(i);
      if ((offset >= bp.getStartOffset()) && (offset <= bp.getEndOffset())) {
        return bp;
      }
    }
    return null;
  }
}
