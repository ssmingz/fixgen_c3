class PlaceHold {
  public void taskStarted(BuildEvent event) {
    Task task = event.getTask();
    TimedElement taskElement = new TimedElement();
    taskElement.startTime = System.currentTimeMillis();
    taskElement.element = doc.createElement(TASK_TAG);
    String name = task.getClass().getName();
    int pos = name.lastIndexOf(".");
    if (pos != (-1)) {
      name = name.substring(pos + 1);
    }
    taskElement.element.setAttribute(NAME_ATTR, name);
    taskElement.element.setAttribute(LOCATION_ATTR, event.getTask().getLocation().toString());
    tasks.put(task, taskElement);
    getStack().push(taskElement);
  }
}
