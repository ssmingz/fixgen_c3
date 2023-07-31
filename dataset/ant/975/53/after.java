class PlaceHold {
  public void execute() throws TaskException {
    if (m_p4View != null) {
      m_syncCmd = m_p4View;
    }
    if ((m_label != null) && (!m_label.equals(""))) {
      m_syncCmd = (m_syncCmd + "@") + m_label;
    }
    final String message = (("Execing sync " + m_p4CmdOpts) + " ") + m_syncCmd;
    getContext().debug(message);
    final String command = (("-s sync " + m_p4CmdOpts) + " ") + m_syncCmd;
    execP4Command(command, null);
  }
}
