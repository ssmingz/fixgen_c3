class PlaceHold {
  public void maybeConfigure() throws BuildException {
    realThing = makeObject(this, getWrapper());
    getWrapper().setProxy(realThing);
    if (realThing instanceof Task) {
      Task task = ((Task) (realThing));
      task.setRuntimeConfigurableWrapper(getWrapper());
      task.setLocation(this.getLocation());
      task.setOwningTarget(this.getOwningTarget());
      task.init();
      this.getOwningTarget().replaceChild(this, ((Task) (realThing)));
    }
    handleChildren(realThing, getWrapper());
    getWrapper().maybeConfigure(getProject());
  }
}
