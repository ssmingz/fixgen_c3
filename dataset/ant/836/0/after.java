class PlaceHold {
  protected FileList getRef(Project p) {
    if (!isChecked()) {
      Stack stk = new Stack();
      stk.push(this);
      dieOnCircularReference(stk, p);
    }
    Object o = getRefid().getReferencedObject(p);
    if (!(o instanceof FileList)) {
      String msg = getRefid().getRefId() + " doesn\'t denote a filelist";
      throw new BuildException(msg);
    } else {
      return ((FileList) (o));
    }
  }
}
