class PlaceHold {
  protected void _prepareRequests(Vector<T> requests) {
    for (int i = 0; i < requests.size(); i++) {
      _prepareRequest(requests.get(i));
    }
  }
}
