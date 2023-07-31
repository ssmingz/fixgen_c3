class PlaceHold {
  public void testTypedAdderAttribute() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("my-role1", "some value");
    RoleManager roleMgr = ((RoleManager) (getComponentManager().lookup(ROLE)));
    roleMgr.addNameRoleMapping("my-role1", ROLE);
    registerConverter(StringToMyRole1Converter.class, String.class, MyRole1.class);
    final ConfigTest6 test = new ConfigTest6();
    m_configurer.configure(test, config, m_context);
    final ConfigTest6 expected = new ConfigTest6();
    expected.add(new MyType1());
    assertEquals(expected, test);
  }
}
