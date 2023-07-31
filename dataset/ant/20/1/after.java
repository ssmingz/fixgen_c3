class PlaceHold{
protected final void doContextualize(final Task task, final Configuration taskModel, final TaskContext context) throws TaskException {
    try {
        if (task instanceof Contextualizable) {
            ((Contextualizable) (task)).contextualize(context);
        }
    } catch (final Throwable throwable) {
        final String message = REZ.getString("compose.error", taskModel.getName(), taskModel.getLocation(), throwable.getMessage());
        throw new TaskException(message, throwable);
    }
}
}