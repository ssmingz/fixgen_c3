class PlaceHold{
public void targetStarted(BuildEvent event) {
    log(">> TARGET STARTED -- " + event.getTarget(), MSG_DEBUG);
    log((StringUtils.LINE_SEP + event.getTarget().getName()) + ":", MSG_INFO);
    targetStartTime = System.currentTimeMillis();
}
}