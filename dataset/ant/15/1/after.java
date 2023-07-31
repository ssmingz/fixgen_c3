class PlaceHold{
public void targetStarted(BuildEvent event) {
    if (Project.MSG_INFO <= msgOutputLevel) {
        String msg = (StringUtils.LINE_SEP + event.getTarget().getName()) + ":";
        out.println(msg);
        log(msg);
    }
}
}