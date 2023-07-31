class PlaceHold{
public void execute() throws BuildException {
    try {
        Method setLocationM = proxy.getClass().getMethod("setLocation", new Class[]{ Location.class });
        if (setLocationM != null) {
            setLocationM.invoke(proxy, new Object[]{ getLocation() });
        }
    } catch (NoSuchMethodException e) {
    } catch (Exception ex) {
        log("Error setting location in " + proxy.getClass(), MSG_ERR);
        throw new BuildException(ex);
    }
    try {
        Method setProjectM = proxy.getClass().getMethod("setProject", new Class[]{ Project.class });
        if (setProjectM != null) {
            setProjectM.invoke(proxy, new Object[]{ getProject() });
        }
    } catch (NoSuchMethodException e) {
    } catch (Exception ex) {
        log("Error setting project in " + proxy.getClass(), MSG_ERR);
        throw new BuildException(ex);
    }
    try {
        DispatchUtils.execute(proxy);
    } catch (BuildException be) {
        throw be;
    } catch (Exception ex) {
        log("Error in " + proxy.getClass(), MSG_VERBOSE);
        throw new BuildException(ex);
    }
}
}