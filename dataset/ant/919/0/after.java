class PlaceHold {
  public Substitution createSubstitution() throws TaskException {
    if (subs != null) {
      throw new TaskException("Only one substitution expression is allowed");
    }
    subs = new Substitution();
    return subs;
  }
}
