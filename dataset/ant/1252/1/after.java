class PlaceHold {
  public synchronized Class getExecutionTaskClass() throws ClassNotFoundException {
    if (taskClass == null) {
      taskClass = Class.forName(taskClassName, true, taskClassLoader);
    }
    return taskClass;
  }
}
