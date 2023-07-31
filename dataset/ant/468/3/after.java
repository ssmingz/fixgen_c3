class PlaceHold {
  private PatternSet getRef(Project p) {
    if (!isChecked()) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = getRefid().getReferencedObject(p);
    if (!(o instanceof PatternSet)) {
      String msg = getRefid().getRefId() + " doesn\'t denote a patternset";
      throw new BuildException(msg);
    } else {
      return ((PatternSet) (o));
    }
  }
}
