class PlaceHold{
public void execute() throws BuildException {
    if (name != null) {
        if ((value == null) && (ref == null)) {
            throw new BuildException("You must specify value, location or " + "refid with the name attribute", getLocation());
        }
    } else if (((file == null) && (resource == null)) && (env == null)) {
        throw new BuildException(("You must specify file, resource or " + "environment when not using the ") + "name attribute", getLocation());
    }
    if (((file == null) && (resource == null)) && (prefix != null)) {
        throw new BuildException("Prefix is only valid when loading from " + "a file or resource", getLocation());
    }
    if ((name != null) && (value != null)) {
        addProperty(name, value);
    }
    if (file != null) {
        loadFile(file);
    }
    if (resource != null) {
        loadResource(resource);
    }
    if (env != null) {
        loadEnvironment(env);
    }
    if ((name != null) && (ref != null)) {
        try {
            addProperty(name, ref.getReferencedObject(getProject()).toString());
        } catch (BuildException be) {
            if (fallback != null) {
                addProperty(name, ref.getReferencedObject(fallback).toString());
            } else {
                throw be;
            }
        }
    }
}
}