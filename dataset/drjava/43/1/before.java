class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    mf = new MainFrame();
    GlobalModel gm = mf.getModel();
    _model = gm.getInteractionsModel();
    _adapter = gm.getSwingInteractionsDocument();
    _doc = gm.getInteractionsDocument();
  }
}
