class PlaceHold{
public Marker createSrcfile() throws TaskException {
    if (srcFilePos != null) {
        throw new TaskException(getName() + " doesn\'t support multiple srcfile elements.");
    }
    srcFilePos = cmdl.createMarker();
    return srcFilePos;
}
}