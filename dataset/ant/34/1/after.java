class PlaceHold{
public synchronized boolean getDefaultexcludes() {
    if (isReference()) {
        return getRef(getProject()).getDefaultexcludes();
    }
    dieOnCircularReference();
    return useDefaultExcludes;
}
}