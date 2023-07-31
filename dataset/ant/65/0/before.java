class PlaceHold{
public static void checkTaskClass(final Class taskClass, final Project project) {
    try {
        final Method executeM = taskClass.getMethod("execute", null);
        if (!Void.TYPE.equals(executeM.getReturnType())) {
            final String message = ((("return type of execute() should be " + "void but was \"") + executeM.getReturnType()) + "\" in ") + taskClass;
            project.log(message, MSG_WARN);
        }
    } catch (NoSuchMethodException e) {
        final String message = "No public execute() in " + taskClass;
        project.log(message, MSG_ERR);
        throw new BuildException(message);
    }
}
}