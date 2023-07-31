class PlaceHold {
  public void execute() throws TaskException {
    if (emptyChangeList == null) {
      emptyChangeList = getEmptyChangeList();
    }
    final Project myProj = getProject();
    P4Handler handler =
        new P4HandlerAdapter() {
          public void process(String line) {
            if (util.match("/Change/", line)) {
              line = util.substitute("s/[^0-9]//g", line);
              int changenumber = Integer.parseInt(line);
              log("Change Number is " + changenumber, MSG_INFO);
              setProperty("p4.change", "" + changenumber);
            } else if (util.match("/error/", line)) {
              throw new TaskException("Perforce Error, check client settings and/or server");
            }
          }
        };
    handler.setOutput(emptyChangeList);
    execP4Command("change -i", handler);
  }
}
