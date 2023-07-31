class PlaceHold {
  protected final void runExecute(Execute exe) throws IOException {
    int err = -1;
    err = exe.execute();
    if (exe.killedProcess()) {
      log("Timeout: killed the sub-process", MSG_WARN);
    }
    maybeSetResultPropertyValue(err);
    if (err != 0) {
      if (failOnError) {
        throw new BuildException((taskType + " returned: ") + err, location);
      } else {
        log("Result: " + err, MSG_ERR);
      }
    }
    if (baos != null) {
      BufferedReader in = new BufferedReader(new StringReader(Execute.toString(baos)));
      String line = null;
      StringBuffer val = new StringBuffer();
      while ((line = in.readLine()) != null) {
        if (val.length() != 0) {
          val.append(LINE_SEP);
        }
        val.append(line);
      }
      getProject().setNewProperty(outputprop, val.toString());
    }
  }
}
