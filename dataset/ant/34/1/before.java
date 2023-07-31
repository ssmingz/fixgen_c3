class PlaceHold{
public synchronized boolean getDefaultexcludes() {
    return isReference() ? getRef(getProject()).getDefaultexcludes() : useDefaultExcludes;
}
}