class PlaceHold {
  protected void doWaitForString(
      final InputStream input, final String string, final Integer timeout) throws TaskException {
    try {
      final StringBuffer sb = new StringBuffer();
      if ((timeout == null) || (timeout.intValue() == 0)) {
        while (sb.toString().indexOf(string) == (-1)) {
          sb.append(((char) (input.read())));
        }
      } else {
        final Calendar endTime = Calendar.getInstance();
        endTime.add(Calendar.SECOND, timeout.intValue());
        while (sb.toString().indexOf(string) == (-1)) {
          while (Calendar.getInstance().before(endTime) && (input.available() == 0)) {
            Thread.sleep(250);
          }
          if (input.available() == 0) {
            throw new TaskException("Response Timed-Out");
          }
          sb.append(((char) (input.read())));
        }
      }
      getContext().info(sb.toString());
    } catch (final TaskException te) {
      throw te;
    } catch (final Exception e) {
      throw new TaskException(e.getMessage(), e);
    }
  }
}
