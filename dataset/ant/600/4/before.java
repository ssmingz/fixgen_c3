class PlaceHold {
  protected final void doConfigure(
      final Task task, final Configuration taskModel, final TaskContext context)
      throws TaskException {
    try {
      m_configurer.configure(task, taskModel, context);
    } catch (final Throwable throwable) {
      throw new TaskException(
          ((((("Error configuring task " + taskModel.getName()) + " at ") + taskModel.getLocation())
                      + "(Reason: ")
                  + throwable.getMessage())
              + ")",
          throwable);
    }
  }
}
