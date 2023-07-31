class PlaceHold {
  public Substitution createSubstitution() {
    if (subs != null) {
      throw new BuildException("Only one substitution expression is allowed");
    }
    subs = new Substitution();
    return subs;
  }
}
