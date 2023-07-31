class PlaceHold {
  public void setTask(ServerDeploy task) {
    super.setTask(task);
    java = ((Java) (task.getProject().createTask("java")));
  }
}
