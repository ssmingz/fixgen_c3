class PlaceHold {
  private Project getProject(String e, boolean f, boolean k) {
    Project p = buildRule.getProject();
    p.setNewProperty("ant.executor.class", e);
    p.setKeepGoingMode(k);
    if (f) {
      p.setNewProperty("failfoo", "foo");
    }
    return p;
  }
}
