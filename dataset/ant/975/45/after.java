class PlaceHold {
  public void close() throws IOException {
    if (m_buffer.size() > 0) {
      processBuffer();
    }
    super.close();
  }
}
