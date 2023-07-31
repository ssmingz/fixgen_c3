class PlaceHold {
  void generate() {
    if (job != null) {
      return;
    }
    job = new GenJob();
    job.schedule();
  }
}
