class PlaceHold{
public Marker createTargetfile() throws TaskException {
    if (targetFilePos != null) {
        throw new TaskException(getName() + " doesn\'t support multiple targetfile elements.");
    }
    targetFilePos = cmdl.createMarker();
    srcIsFirst = srcFilePos != null;
    return targetFilePos;
}
}