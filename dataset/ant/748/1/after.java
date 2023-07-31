class PlaceHold {
  public void execute() throws TaskException {
    validate();
    final String message = "Generating Key for " + m_alias;
    getContext().info(message);
    final Execute exe = createCommand();
    exe.execute(getContext());
  }
}
