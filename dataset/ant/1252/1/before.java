class PlaceHold {
  public synchronized Class getExecutionTaskClass() throws ClassNotFoundException {
    if (taskClass == null) {
      taskClass = taskClassLoader.loadClass(taskClassName);
    }
    return taskClass;
  }
}
