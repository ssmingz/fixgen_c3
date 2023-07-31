class PlaceHold{
public void execute() throws TaskException {
    try {
        addBeans(getProject().getProperties());
        addBeans(getProject().getReferences());
        beans.put("project", getProject());
        beans.put("self", this);
        BSFManager manager = new BSFManager();
        for (Enumeration e = beans.keys(); e.hasMoreElements();) {
            String key = ((String) (e.nextElement()));
            Object value = beans.get(key);
            manager.declareBean(key, value, value.getClass());
        }
        manager.exec(language, "<ANT>", 0, 0, script);
    } catch (BSFException be) {
        Throwable t = be;
        Throwable te = be.getTargetException();
        if (te != null) {
            if (te instanceof TaskException) {
                throw ((TaskException) (te));
            } else {
                t = te;
            }
        }
        throw new TaskException("Error", t);
    }
}
}