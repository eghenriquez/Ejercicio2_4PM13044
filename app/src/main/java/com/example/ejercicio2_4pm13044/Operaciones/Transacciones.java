package com.example.ejercicio2_4pm13044.Operaciones;

public class Transacciones {
    public static final String NameDatabase="PM1DBEje2_4";

    public static final int versionDatabase=1;
    public static final String TbSignature="Signature";

    public static final String Descripcion="Descripcion";
    public static final String Firma="Firma";


    public static final String CreateTableSignature ="CREATE TABLE Signature (Descripcion TEXT,"+"Firma BLOB);";


    public static final String DropTableSignature ="DROP TABLE IF EXISTS Signature;";
}
