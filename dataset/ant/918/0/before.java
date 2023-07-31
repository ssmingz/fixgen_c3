class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    m_deployer = ((Deployer) (getServiceManager().lookup(ROLE)));
    m_converter = ((MasterConverter) (getServiceManager().lookup(ROLE)));
    m_roleManager = ((RoleManager) (getServiceManager().lookup(ROLE)));
    m_roleManager.addRole(new RoleInfo(DataType.ROLE, DATA_TYPE_ROLE, DataType.class));
    m_roleManager.addRole(new RoleInfo(Converter.ROLE, CONVERTER_ROLE, Converter.class));
  }
}
