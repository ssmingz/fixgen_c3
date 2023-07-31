class PlaceHold{
protected AbstractFileSet getRef(Project p) {
    if (!isChecked()) {
        Stack stk = new Stack();
        stk.push(this);
        dieOnCircularReference(stk, p);
    }
    Object o = getRefid().getReferencedObject(p);
    if (!(o instanceof FileSet)) {
        String msg = getRefid().getRefId() + " doesn\'t denote a fileset";
        throw new BuildException(msg);
    } else {
        return ((AbstractFileSet) (o));
    }
}
}