class PlaceHold {
  protected void logStats(long timeStarted, long timeEnded, long totalLength) {
    double duration = (timeEnded - timeStarted) / ONE_SECOND;
    NumberFormat format = NumberFormat.getNumberInstance();
    format.setMaximumFractionDigits(2);
    format.setMinimumFractionDigits(1);
    listener.log(
        ((("File transfer time: " + format.format(duration)) + " Average Rate: ")
                + format.format(totalLength / duration))
            + " B/s");
  }
}
