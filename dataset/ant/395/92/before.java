class PlaceHold {
  protected Task makeTask(UnknownElement ue, RuntimeConfigurable w, boolean onTopLevel) {
    Task task = project.createTask(ue.getTag());
    if ((task == null) && (!onTopLevel)) {
      throw getNotFoundException("task", ue.getTag());
    }
    if (task != null) {
      task.setLocation(getLocation());
      task.setOwningTarget(target);
      task.init();
    }
    return task;
  }
}
