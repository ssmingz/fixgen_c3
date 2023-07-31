class PlaceHold{
protected final void doContextualize(final Task task, final Configuration taskModel, final TaskContext context) throws TaskException {
    try {
        if (task instanceof Contextualizable) {
            ((Contextualizable) (task)).contextualize(context);
        }
    } catch (final Throwable throwable) {
        throw new TaskException(((((("Error contextualizing task " + taskModel.getName()) + " at ") + taskModel.getLocation()) + "(Reason: ") + throwable.getMessage()) + ")", throwable);
    }
}
}