class PlaceHold {
  public void taskStarted(BuildEvent event) {
    TimedElement taskElement = new TimedElement();
    taskElement.startTime = System.currentTimeMillis();
    taskElement.element = doc.createElement(TASK_TAG);
    Task task = event.getTask();
    String name = event.getTask().getTaskName();
    if (name == null) {
      name = "";
    }
    taskElement.element.setAttribute(NAME_ATTR, name);
    taskElement.element.setAttribute(LOCATION_ATTR, event.getTask().getLocation().toString());
    tasks.put(task, taskElement);
    getStack().push(taskElement);
  }
}
