class PlaceHold{
public void initChecksum() {
    if (checksum != null) {
        return;
    }
    if ("CRC".equals(algorithm)) {
        checksum = new CRC32();
    } else if ("ADLER".equals(algorithm)) {
        checksum = new Adler32();
    } else {
        throw new BuildException(new NoSuchAlgorithmException());
    }
}
}