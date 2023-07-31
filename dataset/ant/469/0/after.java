class PlaceHold {
  public void taskStarted(BuildEvent event) {
    Task task = event.getTask();
    TimedElement taskElement = new TimedElement();
    taskElement.startTime = System.currentTimeMillis();
    taskElement.element = doc.createElement(TASK_TAG);
    String name = event.getTask().getTaskName();
    taskElement.element.setAttribute(NAME_ATTR, name);
    taskElement.element.setAttribute(LOCATION_ATTR, event.getTask().getLocation().toString());
    tasks.put(task, taskElement);
    getStack().push(taskElement);
  }
}
