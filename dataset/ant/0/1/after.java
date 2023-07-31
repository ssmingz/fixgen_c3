class PlaceHold{
public void buildFinished(BuildEvent event) {
    postEvent(event, BUILD_FINISHED);
    _context.getEventBus().postEvent(new BuildFinishedEvent(_context, event));
}
}