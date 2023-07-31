class PlaceHold{
public void execute() {
    _context.setProject(null);
    _context.getEventBus().postEvent(new ProjectClosedEvent(_context));
}
}