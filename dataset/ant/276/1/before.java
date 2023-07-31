class PlaceHold {
  private TaskContext configureTask(BuildElement model) throws ExecutionException {
    String taskType = model.getType();
    ImportInfo taskDefInfo = componentManager.getDefinition(taskType);
    if ((taskDefInfo == null) || (taskDefInfo.getDefinitionType() != AntLibrary.TASKDEF)) {
      throw new ExecutionException(
          (("There is no defintion for a " + "task of type <") + taskType) + ">",
          model.getLocation());
    }
    String className = taskDefInfo.getClassName();
    ComponentLibrary componentLibrary = taskDefInfo.getComponentLibrary();
    try {
      ClassLoader taskClassLoader = componentLibrary.getClassLoader();
      Class elementClass = Class.forName(className, true, taskClassLoader);
      AntLibFactory libFactory = componentManager.getLibFactory(componentLibrary);
      Object element = libFactory.createTaskInstance(elementClass);
      Task task = null;
      if (element instanceof Task) {
        task = ((Task) (element));
      } else {
        task = new TaskAdapter(taskType, element);
      }
      ClassLoader currentLoader = setContextLoader(taskClassLoader);
      TaskContext taskContext = new TaskContext(this);
      taskContext.init(taskClassLoader, task, model);
      configureElement(libFactory, element, model);
      task.validateComponent();
      setContextLoader(currentLoader);
      return taskContext;
    } catch (ClassNotFoundException e) {
      throw new ExecutionException(
          ((("Class " + className) + " for task <") + taskType) + "> was not found",
          e,
          model.getLocation());
    } catch (NoClassDefFoundError e) {
      throw new ExecutionException(
          (("Could not load a dependent class (" + e.getMessage()) + ") for task ") + taskType,
          e,
          model.getLocation());
    } catch (InstantiationException e) {
      throw new ExecutionException(
          ((("Unable to instantiate task class " + className) + " for task <") + taskType) + ">",
          e,
          model.getLocation());
    } catch (IllegalAccessException e) {
      throw new ExecutionException(
          ((("Unable to access task class " + className) + " for task <") + taskType) + ">",
          e,
          model.getLocation());
    } catch (ExecutionException e) {
      e.setLocation(model.getLocation(), false);
      throw e;
    } catch (RuntimeException e) {
      throw new ExecutionException(
          (e.getClass().getName() + ": ") + e.getMessage(), e, model.getLocation());
    }
  }
}
