class PlaceHold {
  public void configure(Object realObject) {
    if (realObject == null) {
      return;
    }
    realThing = realObject;
    getWrapper().setProxy(realThing);
    Task task = null;
    if (realThing instanceof Task) {
      task = ((Task) (realThing));
      task.setRuntimeConfigurableWrapper(getWrapper());
      if (getWrapper().getId() != null) {
        this.getOwningTarget().replaceChild(this, ((Task) (realThing)));
      }
    }
    if (task != null) {
      task.maybeConfigure();
    } else {
      getWrapper().maybeConfigure(getProject());
    }
    handleChildren(realThing, getWrapper());
  }
}
