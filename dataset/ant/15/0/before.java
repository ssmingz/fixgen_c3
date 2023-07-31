class PlaceHold{
public void targetStarted(BuildEvent event) {
    log(">> TARGET STARTED -- " + event.getTarget(), MSG_DEBUG);
    log((lSep + event.getTarget().getName()) + ":", MSG_INFO);
    targetStartTime = System.currentTimeMillis();
}
}