class PlaceHold{
public FileNameMapper getImplementation() throws BuildException {
    if (isReference()) {
        return getRef().getImplementation();
    }
    if (type == null) {
        throw new BuildException("type attribute is required");
    }
    try {
        Class c = Class.forName(type.getImplementation());
        FileNameMapper m = ((FileNameMapper) (c.newInstance()));
        m.setFrom(from);
        m.setTo(to);
        return m;
    } catch (Throwable t) {
        throw new BuildException(t);
    }
}
}