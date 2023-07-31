class PlaceHold {
  private void checkRemoteSensitivity(FTPFile[] array, String directory) {
    boolean candidateFound = false;
    String target = null;
    for (int icounter = 0; icounter < array.length; icounter++) {
      if (array[icounter].isDirectory()) {
        if ((!array[icounter].getName().equals(".")) && (!array[icounter].getName().equals(".."))) {
          candidateFound = true;
          target = fiddleName(array[icounter].getName());
          getProject()
              .log(
                  ((("will try to cd to " + target) + " where a directory called ")
                          + array[icounter].getName())
                      + " exists",
                  MSG_DEBUG);
          for (int pcounter = 0; pcounter < array.length; pcounter++) {
            if (array[pcounter].getName().equals(target) && (pcounter != icounter)) {
              candidateFound = false;
            }
          }
          if (candidateFound) {
            break;
          }
        }
      }
    }
    if (candidateFound) {
      try {
        getProject().log("testing case sensitivity, attempting to cd to " + target, MSG_DEBUG);
        remoteSystemCaseSensitive = !ftp.changeWorkingDirectory(target);
      } catch (IOException ioe) {
        remoteSystemCaseSensitive = true;
      } finally {
        try {
          ftp.changeWorkingDirectory(directory);
        } catch (IOException ioe) {
          throw new BuildException(ioe, getLocation());
        }
      }
      getProject()
          .log("remote system is case sensitive : " + remoteSystemCaseSensitive, MSG_VERBOSE);
      remoteSensitivityChecked = true;
    }
  }
}
