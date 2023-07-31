class PlaceHold {
  private void addReferences() throws BuildException {
    Hashtable thisReferences = ((Hashtable) (project.getReferences().clone()));
    Hashtable newReferences = newProject.getReferences();
    Enumeration e;
    if (references.size() > 0) {
      for (e = references.elements(); e.hasMoreElements(); ) {
        Reference ref = ((Reference) (e.nextElement()));
        String refid = ref.getRefId();
        if (refid == null) {
          throw new BuildException("the refid attribute is required" + " for reference elements");
        }
        if (!thisReferences.containsKey(refid)) {
          log(("Parent project doesn't contain any reference '" + refid) + "'", MSG_WARN);
          continue;
        }
        thisReferences.remove(refid);
        String toRefid = ref.getToRefid();
        if (toRefid == null) {
          toRefid = refid;
        }
        copyReference(refid, toRefid);
      }
    }
    if (inheritRefs) {
      for (e = thisReferences.keys(); e.hasMoreElements(); ) {
        String key = ((String) (e.nextElement()));
        if (newReferences.containsKey(key)) {
          continue;
        }
        copyReference(key, key);
      }
    }
  }
}
