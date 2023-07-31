class PlaceHold{
public void targetStarted(BuildEvent event) {
    if (Project.MSG_INFO <= msgOutputLevel) {
        String msg = (lSep + event.getTarget().getName()) + ":";
        out.println(msg);
        log(msg);
    }
}
}