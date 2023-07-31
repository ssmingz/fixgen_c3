class PlaceHold {
  private void executeTask(final Configuration task, final ExecutionFrame frame)
      throws TaskException {
    final String name = task.getName();
    final String message = REZ.getString("exec-task.notice", name);
    getLogger().debug(message);
    frame.getContext().setProperty(NAME, name);
    m_listenerSupport.taskStarted(name);
    m_executor.execute(task, frame);
    m_listenerSupport.taskFinished();
  }
}
