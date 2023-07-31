class PlaceHold {
  protected void forwardRegion() {
    BrowserHistoryManager rm = _model.getBrowserHistoryManager();
    _frame.addToBrowserHistory();
    BrowserDocumentRegion r = rm.nextCurrentRegion(_frame.getModel().getNotifier());
    updateButtons();
    RegionListUserObj<BrowserDocumentRegion> userObj = getUserObjForRegion(r);
    if (userObj != null) {
      _list.ensureIndexIsVisible(_listModel.indexOf(userObj));
    }
    _frame.scrollToDocumentAndOffset(r.getDocument(), r.getStartOffset(), false, false);
  }
}
