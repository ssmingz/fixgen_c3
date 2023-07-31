class PlaceHold {
  private PatternSet getRef(Project p) throws TaskException {
    if (!checked) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = ref.getReferencedObject(p);
    if (!(o instanceof PatternSet)) {
      String msg = ref.getRefId() + " doesn\'t denote a patternset";
      throw new TaskException(msg);
    } else {
      return ((PatternSet) (o));
    }
  }
}
