class PlaceHold {
  public void waitForString(String s, Integer timeout) {
    InputStream is = this.getInputStream();
    try {
      StringBuffer sb = new StringBuffer();
      if ((timeout == null) || (timeout.intValue() == 0)) {
        while (sb.toString().indexOf(s) == (-1)) {
          sb.append(((char) (is.read())));
        }
      } else {
        Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.SECOND, timeout.intValue());
        while (sb.toString().indexOf(s) == (-1)) {
          while (Calendar.getInstance().before(endTime) && (is.available() == 0)) {
            Thread.sleep(250);
          }
          if (is.available() == 0) {
            log("Read before running into timeout: " + sb.toString(), MSG_DEBUG);
            throw new BuildException(
                ("Response timed-out waiting for \"" + s) + '\"', getLocation());
          }
          sb.append(((char) (is.read())));
        }
      }
      log(sb.toString(), MSG_INFO);
    } catch (BuildException be) {
      throw be;
    } catch (Exception e) {
      throw new BuildException(e, getLocation());
    }
  }
}
