class PlaceHold {
  protected Link ignoreViolatedAssumptions(Link next) {
    return new IgnoreViolatedAssumptions(next);
  }
}
