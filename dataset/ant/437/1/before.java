class PlaceHold {
  public void init() {
    callee = ((Ant) (getProject().createTask("ant")));
    callee.setOwningTarget(getOwningTarget());
    callee.setTaskName(getTaskName());
    callee.setLocation(getLocation());
    callee.init();
  }
}
