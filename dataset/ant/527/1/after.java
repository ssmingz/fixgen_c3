class PlaceHold {
  public synchronized void add(ResourceCollection c) {
    if (rc == null) {
      rc = c;
      return;
    }
    if (!(rc instanceof Resources)) {
      Resources newRc = new Resources();
      newRc.setProject(getProject());
      newRc.setCache(true);
      newRc.add(rc);
      rc = newRc;
    }
    ((Resources) (rc)).add(c);
  }
}
