class PlaceHold {
  public void beginSession() {
    getPaintSurface()
        .getPaintStatus()
        .setMessage(PaintPlugin.getResourceString("session.Text.message"));
  }
}
