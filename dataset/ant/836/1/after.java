class PlaceHold {
  public Substitution getRef(Project p) {
    if (!isChecked()) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = getRefid().getReferencedObject(p);
    if (!(o instanceof Substitution)) {
      String msg = getRefid().getRefId() + " doesn\'t denote a substitution";
      throw new BuildException(msg);
    } else {
      return ((Substitution) (o));
    }
  }
}
