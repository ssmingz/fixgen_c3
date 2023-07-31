class PlaceHold {
  private void changelist_stdout(String line) {
    if (!util.match("/^#/", line)) {
      if (util.match("/error/", line)) {
        getContext().debug("Client Error");
        registerError(new TaskException("Perforce Error, check client settings and/or server"));
      } else if (util.match("/<enter description here>/", line)) {
        m_description = backslash(m_description);
        line = util.substitute(("s/<enter description here>/" + m_description) + "/", line);
      } else if (util.match("/\\/\\//", line)) {
        return;
      }
      m_changelistData.append(line);
      m_changelistData.append("\n");
    }
  }
}
