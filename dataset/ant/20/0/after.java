class PlaceHold{
protected final void doConfigure(final Task task, final Configuration taskModel, final TaskContext context) throws TaskException {
    try {
        m_configurer.configure(task, taskModel, context);
    } catch (final Throwable throwable) {
        final String message = REZ.getString("config.error", taskModel.getName(), taskModel.getLocation(), throwable.getMessage());
        throw new TaskException(message, throwable);
    }
}
}