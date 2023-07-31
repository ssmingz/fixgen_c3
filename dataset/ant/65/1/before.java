class PlaceHold{
public void checkTaskClass(final Class taskClass) throws BuildException {
    ComponentHelper.getComponentHelper(this).checkTaskClass(taskClass);
    if (!Modifier.isPublic(taskClass.getModifiers())) {
        final String message = taskClass + " is not public";
        log(message, Project.MSG_ERR);
        throw new BuildException(message);
    }
    if (Modifier.isAbstract(taskClass.getModifiers())) {
        final String message = taskClass + " is abstract";
        log(message, Project.MSG_ERR);
        throw new BuildException(message);
    }
    try {
        taskClass.getConstructor(null);
    } catch (NoSuchMethodException e) {
        final String message = "No public no-arg constructor in " + taskClass;
        log(message, Project.MSG_ERR);
        throw new BuildException(message);
    }
    if (!Task.class.isAssignableFrom(taskClass)) {
        TaskAdapter.checkTaskClass(taskClass, this);
    }
}
}