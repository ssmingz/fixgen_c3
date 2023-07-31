class PlaceHold{
public void buildFinished(BuildEvent event) {
    postEvent(event, BUILD_FINISHED);
}
}