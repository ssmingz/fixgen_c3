class PlaceHold {
  public Substitution getRef(Project p) {
    if (!checked) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = ref.getReferencedObject(p);
    if (!(o instanceof Substitution)) {
      String msg = ref.getRefId() + " doesn\'t denote a substitution";
      throw new BuildException(msg);
    } else {
      return ((Substitution) (o));
    }
  }
}
