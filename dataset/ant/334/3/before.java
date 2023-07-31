class PlaceHold {
  public void loadProjects(Vector projectDescriptions) {
    try {
      String request = ("http://" + remoteServer) + "/servlet/vajload?";
      String delimiter = "";
      for (Enumeration e = projectDescriptions.elements(); e.hasMoreElements(); ) {
        VAJProjectDescription pd = ((VAJProjectDescription) (e.nextElement()));
        request =
            (((((((request + delimiter) + VAJLoadServlet.PROJECT_NAME_PARAM) + "=")
                                + pd.getName().replace(' ', '+'))
                            + "&")
                        + VAJLoadServlet.VERSION_PARAM)
                    + "=")
                + pd.getVersion().replace(' ', '+');
        delimiter = "&";
      }
      sendRequest(request);
    } catch (Exception ex) {
      throw new BuildException(ex);
    }
  }
}
